package com.cheny.projectsteward.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheny.projectsteward.annotation.AuthCheck;
import com.cheny.projectsteward.common.BaseResponse;
import com.cheny.projectsteward.common.DeleteRequest;
import com.cheny.projectsteward.common.ErrorCode;
import com.cheny.projectsteward.common.ResultUtils;
import com.cheny.projectsteward.constant.UserConstant;
import com.cheny.projectsteward.exception.BusinessException;
import com.cheny.projectsteward.exception.ThrowUtils;
import com.cheny.projectsteward.model.dto.department.DepartmentAddRequest;
import com.cheny.projectsteward.model.dto.department.DepartmentQueryRequest;
import com.cheny.projectsteward.model.dto.department.DepartmentUpdateRequest;
import com.cheny.projectsteward.model.entity.Department;
import com.cheny.projectsteward.model.entity.User;
import com.cheny.projectsteward.model.vo.department.DepartmentVO;
import com.cheny.projectsteward.service.DepartmentService;
import com.cheny.projectsteward.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 院系接口
 *
 */
@RestController
@RequestMapping("/department")
@Slf4j
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    @Resource
    private UserService userService;


    /**
     * 创建院系
     *
     * @param departmentAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addDepartment(@RequestBody DepartmentAddRequest departmentAddRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(departmentAddRequest == null, ErrorCode.PARAMS_ERROR);
        Department department = new Department();
        BeanUtils.copyProperties(departmentAddRequest, department);
        departmentService.validDepartment(department, true);
        boolean result = departmentService.save(department);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newDepartmentId = department.getId();
        return ResultUtils.success(newDepartmentId);
    }

    /**
     * 删除院系
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteDepartment(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        Department oldDepartment = departmentService.getById(id);
        ThrowUtils.throwIf(oldDepartment == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅管理员可删除
        //if (!oldDepartment.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
        //    throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        //}
        // 操作数据库
        boolean result = departmentService.removeById(id);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * 更新院系（仅管理员可用）
     *
     * @param departmentUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateDepartment(@RequestBody DepartmentUpdateRequest departmentUpdateRequest) {
        if (departmentUpdateRequest == null || departmentUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Department department = new Department();
        BeanUtils.copyProperties(departmentUpdateRequest, department);
        // 数据校验
        departmentService.validDepartment(department, false);
        // 判断是否存在
        long id = departmentUpdateRequest.getId();
        Department oldDepartment = departmentService.getById(id);
        ThrowUtils.throwIf(oldDepartment == null, ErrorCode.NOT_FOUND_ERROR);
        // 操作数据库
        boolean result = departmentService.updateById(department);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }


    /**
     * 获取院系列表
     * @param departmentQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<DepartmentVO>> listDepartmentVO(@RequestBody DepartmentQueryRequest departmentQueryRequest,
                                                                   HttpServletRequest request) {
        long current = departmentQueryRequest.getCurrent();
        long size = departmentQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        // 查询数据库
        Page<Department> departmentPage = departmentService.page(new Page<>(current, size),
                departmentService.getQueryWrapper(departmentQueryRequest));
       // 获取封装类
        return ResultUtils.success(departmentService.getDepartmentVOPage(departmentPage, request));
    }
}