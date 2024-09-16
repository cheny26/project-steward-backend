package com.cheny.projectSteward.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cheny.projectSteward.model.dto.department.DepartmentQueryRequest;
import com.cheny.projectSteward.model.entity.Department;
import com.cheny.projectSteward.model.vo.department.DepartmentVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 院系服务
 *
 */
public interface DepartmentService extends IService<Department> {

    /**
     * 校验数据
     *
     * @param department
     * @param add 对创建的数据进行校验
     */
    void validDepartment(Department department, boolean add);

    /**
     * 获取查询条件
     *
     * @param departmentQueryRequest
     * @return
     */
    QueryWrapper<Department> getQueryWrapper(DepartmentQueryRequest departmentQueryRequest);
    
    /**
     * 获取院系封装
     *
     * @param department
     * @param request
     * @return
     */
    DepartmentVO getDepartmentVO(Department department, HttpServletRequest request);

    /**
     * 分页获取院系封装
     *
     * @param departmentPage
     * @param request
     * @return
     */
    Page<DepartmentVO> getDepartmentVOPage(Page<Department> departmentPage, HttpServletRequest request);
}
