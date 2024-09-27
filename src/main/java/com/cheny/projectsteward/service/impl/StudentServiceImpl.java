package com.cheny.projectsteward.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheny.projectsteward.common.ErrorCode;
import com.cheny.projectsteward.exception.BusinessException;
import com.cheny.projectsteward.mapper.StudentMapper;
import com.cheny.projectsteward.model.dto.student.StudentRegisterRequest;
import com.cheny.projectsteward.model.entity.Student;

import com.cheny.projectsteward.model.entity.User;
import com.cheny.projectsteward.model.vo.LoginUserVO;
import com.cheny.projectsteward.model.vo.UserVO;
import com.cheny.projectsteward.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 学生服务实现
 *
 */
@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    /**
     * 盐值，混淆密码
     */
    public static final String SALT = "chen";

    @Override
    public long register(StudentRegisterRequest studentRegisterRequest) {
        String studentId=studentRegisterRequest.getStudentId();
        String password=studentRegisterRequest.getPassword();
        String checkPassword=studentRegisterRequest.getCheckPassword();
        // 1. 校验
        if (studentId.length() !=10) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "学号错误");
        }
        if (password.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码过短");
        }
        // 密码和校验密码相同
        if (!password.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码不一致");
        }
        // 账户不能重复
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", studentId);
        long count = this.baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "该学号已存在");
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        // 3. 插入数据
        Student student = new Student();
        student.setPassword(encryptPassword);
        BeanUtils.copyProperties(studentRegisterRequest,student);
        boolean saveResult = this.save(student);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
        }
        return student.getId();
    }

    @Override
    public Student login(String studentId, String password) {
        // 1. 校验
        if (studentId.length() !=10) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "学号错误");
        }
        if (password.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        // 查询用户是否存在
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", studentId);
        queryWrapper.eq("password", encryptPassword);
        Student student = this.baseMapper.selectOne(queryWrapper);
        // 用户不存在
        if (student == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "学号不存在或密码错误");
        }
        // 3. 记录用户的登录态
        StpUtil.login(studentId);
        return student;

    }


    @Override
    public Student getLoginUser(HttpServletRequest request) {
        return null;
    }

    @Override
    public User getLoginUserPermitNull(HttpServletRequest request) {
        return null;
    }

    @Override
    public boolean isAdmin(HttpServletRequest request) {
        return false;
    }

    @Override
    public boolean isAdmin(User student) {
        return false;
    }

    @Override
    public boolean studentLogout(HttpServletRequest request) {
        return false;
    }

    @Override
    public LoginUserVO getLoginUserVO(User student) {
        return null;
    }

    @Override
    public UserVO getUserVO(User student) {
        return null;
    }

    @Override
    public List<UserVO> getUserVO(List<User> studentList) {
        return null;
    }
}
