package com.cheny.projectsteward.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.cheny.projectsteward.common.BaseResponse;
import com.cheny.projectsteward.common.ResultUtils;
import com.cheny.projectsteward.model.dto.teacher.TeacherLoginRequest;
import com.cheny.projectsteward.model.dto.teacher.TeacherRegisterRequest;
import com.cheny.projectsteward.model.entity.Teacher;
import com.cheny.projectsteward.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

/**
 * 教师接口
 *
 */
@RestController
@RequestMapping("/teacher")
@Slf4j
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @PostMapping("/register")
    public BaseResponse<Long> register(@Validated @RequestBody TeacherRegisterRequest teacherRegisterRequest){
        long result = teacherService.register(teacherRegisterRequest);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<Teacher> login(@Validated @RequestBody TeacherLoginRequest teacherLoginRequest){
        Teacher teacher = teacherService.login(teacherLoginRequest.getEmployeeId(), teacherLoginRequest.getPassword());
        return ResultUtils.success(teacher);
    }

    @GetMapping ("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }
}