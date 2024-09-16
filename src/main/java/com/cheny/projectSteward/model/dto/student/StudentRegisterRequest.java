package com.cheny.projectSteward.model.dto.student;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chen_y
 * @date 2024-09-14 0:16
 */
@Data
public class StudentRegisterRequest implements Serializable {

    /**
     * 学号
     */
    private String studentId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年级
     */
    private Integer grade;

    /**
     * 学院ID，外键关联院系表
     */
    private Long departmentId;

    /**
     * 专业
     */
    private String major;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码
     */
    private String checkPassword;


    private static final long serialVersionUID = 1L;
}
