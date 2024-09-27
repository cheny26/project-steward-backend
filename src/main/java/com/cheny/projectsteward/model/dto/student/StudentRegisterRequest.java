package com.cheny.projectsteward.model.dto.student;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author chen_y
 * @date 2024-09-14 0:16
 */
@Data
public class StudentRegisterRequest implements Serializable {

    @NotBlank(message = "学号不能为空")
    private String studentId;

    @NotBlank(message = "姓名不能为空")
    private String name;


    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    private String checkPassword;

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


    private static final long serialVersionUID = 1L;
}
