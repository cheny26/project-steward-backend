package com.cheny.projectsteward.service;

import com.cheny.projectsteward.model.dto.student.StudentRegisterRequest;
import com.cheny.projectsteward.model.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cheny.projectsteward.model.entity.User;
import com.cheny.projectsteward.model.vo.LoginUserVO;
import com.cheny.projectsteward.model.vo.UserVO;
import jakarta.servlet.http.HttpServletRequest;


import java.util.List;

/**
* @author chen
* @description 针对表【student(学生表)】的数据库操作Service
* @createDate 2024-09-13 23:52:07
*/
public interface StudentService extends IService<Student> {
    /**
     * @param studentRegisterRequest  注册
     * @return 新用户 id
     */
    long register(StudentRegisterRequest studentRegisterRequest);

    /**
     * @param studentId  学号
     * @param password  密码
     * @return 脱敏后的用户信息
     */
    Student login(String studentId, String password);


    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    Student getLoginUser(HttpServletRequest request);


    /**
     * 获取当前登录用户（允许未登录）
     *
     * @param request
     * @return
     */
    User getLoginUserPermitNull(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param student
     * @return
     */
    boolean isAdmin(User student);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    boolean studentLogout(HttpServletRequest request);

    /**
     * 获取脱敏的已登录用户信息
     *
     * @return
     */
    LoginUserVO getLoginUserVO(User student);

    /**
     * 获取脱敏的用户信息
     *
     * @param student
     * @return
     */
    UserVO getUserVO(User student);

    /**
     * 获取脱敏的用户信息
     *
     * @param studentList
     * @return
     */
    List<UserVO> getUserVO(List<User> studentList);
}
