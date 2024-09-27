package com.cheny.projectsteward.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.cheny.projectsteward.common.BaseResponse;
import com.cheny.projectsteward.common.ErrorCode;
import com.cheny.projectsteward.common.ResultUtils;
import com.cheny.projectsteward.exception.BusinessException;
import com.cheny.projectsteward.model.dto.student.StudentLoginRequest;
import com.cheny.projectsteward.model.dto.student.StudentRegisterRequest;
import com.cheny.projectsteward.model.entity.Student;
import com.cheny.projectsteward.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

/**
 * 学生接口
 *
 */
@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Resource
    private StudentService studentService;

    @PostMapping("/register")
    public BaseResponse<Long> studentRegister(@RequestBody StudentRegisterRequest studentRegisterRequest){
        if (studentRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String studentId = studentRegisterRequest.getStudentId();
        String password = studentRegisterRequest.getPassword();
        String checkPassword = studentRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(studentId, password, checkPassword)) {
            return null;
        }
        long result = studentService.register(studentRegisterRequest);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<Student> login(@RequestBody StudentLoginRequest studentLoginRequest){
        if (studentLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String studentId = studentLoginRequest.getStudentId();
        String password = studentLoginRequest.getPassword();
        if (StringUtils.isAnyBlank(studentId, password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Student student = studentService.login(studentId, password);
        return ResultUtils.success(student);
    }

    @GetMapping ("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }
}