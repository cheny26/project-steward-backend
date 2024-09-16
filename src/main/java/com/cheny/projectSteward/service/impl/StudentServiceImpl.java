package com.cheny.projectSteward.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheny.projectSteward.common.ErrorCode;
import com.cheny.projectSteward.exception.BusinessException;
import com.cheny.projectSteward.mapper.StudentMapper;
import com.cheny.projectSteward.model.dto.student.StudentRegisterRequest;
import com.cheny.projectSteward.model.entity.Student;

import com.cheny.projectSteward.model.entity.User;
import com.cheny.projectSteward.model.vo.LoginUserVO;
import com.cheny.projectSteward.model.vo.UserVO;
import com.cheny.projectSteward.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.cheny.projectSteward.constant.UserConstant.USER_LOGIN_STATE;


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
        if (StringUtils.isAnyBlank(studentId, password, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
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
        if (StringUtils.isAnyBlank(studentId, password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
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
        //request.getSession().setAttribute(USER_LOGIN_STATE, student);
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
