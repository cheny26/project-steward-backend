package com.cheny.projectSteward.model.dto.department;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 创建院系请求
 *
 */
@Data
public class DepartmentAddRequest implements Serializable {

    /**
     * 院系名称
     */
    private String name;

    /**
     * 负责人
     */
    private String manager;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    private static final long serialVersionUID = 1L;
}