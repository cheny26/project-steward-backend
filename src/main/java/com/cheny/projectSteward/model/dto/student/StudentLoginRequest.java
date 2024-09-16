package com.cheny.projectSteward.model.dto.student;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求
 *
 */
@Data
public class StudentLoginRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String studentId;

    private String password;
}
