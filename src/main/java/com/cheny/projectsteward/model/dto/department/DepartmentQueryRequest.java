package com.cheny.projectsteward.model.dto.department;

import com.cheny.projectsteward.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询院系请求
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DepartmentQueryRequest extends PageRequest implements Serializable {

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