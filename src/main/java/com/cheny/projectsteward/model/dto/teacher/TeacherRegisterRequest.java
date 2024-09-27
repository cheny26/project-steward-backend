package com.cheny.projectsteward.model.dto.teacher;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户注册请求体
 *
 */
@Data
@NotNull(message = "参数为空")
public class TeacherRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    @NotBlank(message = "工号不能为空")
    private String employeeId;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    private String checkPassword;

    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 职称
     */
    private String title;

    /**
     * 学院ID，外键关联院系表
     */
    private Long departmentId;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;


    /**
     * 头像URL
     */
    private String avatar;
}
