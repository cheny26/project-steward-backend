package com.cheny.projectSteward.model.vo.department;

import cn.hutool.json.JSONUtil;
import com.cheny.projectSteward.model.entity.Department;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 院系视图
 *
 */
@Data
public class DepartmentVO implements Serializable {

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

    /**
     * 封装类转对象
     *
     * @param departmentVO
     * @return
     */
    public static Department voToObj(DepartmentVO departmentVO) {
        if (departmentVO == null) {
            return null;
        }
        Department department = new Department();
        BeanUtils.copyProperties(departmentVO, department);
        return department;
    }

    /**
     * 对象转封装类
     *
     * @param department
     * @return
     */
    public static DepartmentVO objToVo(Department department) {
        if (department == null) {
            return null;
        }
        DepartmentVO departmentVO = new DepartmentVO();
        BeanUtils.copyProperties(department, departmentVO);
        return departmentVO;
    }
}
