package com.cheny.projectsteward.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheny.projectsteward.common.ErrorCode;
import com.cheny.projectsteward.exception.BusinessException;
import com.cheny.projectsteward.model.dto.teacher.TeacherRegisterRequest;
import com.cheny.projectsteward.model.entity.Teacher;
import com.cheny.projectsteward.model.enums.TeacherRoleEnum;
import com.cheny.projectsteward.service.TeacherService;
import com.cheny.projectsteward.mapper.TeacherMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
* @author chen
* @description 针对表【teacher(教师表)】的数据库操作Service实现
* @createDate 2024-09-13 23:52:07
*/
@Service
@Slf4j
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService{

    /**
     * 盐值，混淆密码
     */
    public static final String SALT = "chen";

    @Override
    public long register(TeacherRegisterRequest teacherRegisterRequest) {
        String employeeId=teacherRegisterRequest.getEmployeeId();
        String password=teacherRegisterRequest.getPassword();
        String checkPassword=teacherRegisterRequest.getCheckPassword();
        // 1. 校验
        if (employeeId.length() !=9) {
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
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("employee_id", employeeId);
        long count = this.baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "该工号已存在");
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        // 3. 插入数据
        Teacher teacher=new Teacher();
        teacher.setPassword(encryptPassword);
        BeanUtils.copyProperties(teacherRegisterRequest,teacher);
        boolean saveResult = this.save(teacher);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
        }
        return teacher.getId();
    }

    @Override
    public Teacher login(String employeeId, String password) {
        // 1. 校验
        if (employeeId.length() !=9) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "学号错误");
        }
        if (password.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        // 查询用户是否存在
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("employee_Id", employeeId);
        queryWrapper.eq("password", encryptPassword);
        Teacher teacher = this.baseMapper.selectOne(queryWrapper);
        // 用户不存在
        if (teacher == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "学号不存在或密码错误");
        }
        StpUtil.login(employeeId);
        return teacher;

    }

    @Override
    public boolean isAdmin(String employeeId) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("employee_Id", employeeId);
        Teacher teacher = this.baseMapper.selectOne(queryWrapper);
        // 用户不存在
        if (teacher == null) {
            log.info("工号错误");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "工号错误");
        }
        return teacher.getRole()== TeacherRoleEnum.ADMIN.getValue();
    }
}




