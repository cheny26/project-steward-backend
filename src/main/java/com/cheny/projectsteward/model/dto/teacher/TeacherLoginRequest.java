package com.cheny.projectsteward.model.dto.teacher;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户登录请求
 *
 */
@Data
public class TeacherLoginRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    @NotBlank(message = "工号不能为空")
    private String employeeId;

    @NotBlank(message = "密码不能为空")
    private String password;
}
