package com.cheny.projectSteward.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheny.projectSteward.common.ErrorCode;
import com.cheny.projectSteward.constant.CommonConstant;
import com.cheny.projectSteward.exception.ThrowUtils;
import com.cheny.projectSteward.mapper.DepartmentMapper;
import com.cheny.projectSteward.model.dto.department.DepartmentQueryRequest;
import com.cheny.projectSteward.model.entity.Department;
import com.cheny.projectSteward.model.entity.User;
import com.cheny.projectSteward.model.vo.UserVO;
import com.cheny.projectSteward.model.vo.department.DepartmentVO;
import com.cheny.projectSteward.service.DepartmentService;
import com.cheny.projectSteward.service.UserService;
import com.cheny.projectSteward.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 院系服务实现
 *
 */
@Service
@Slf4j
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Resource
    private UserService userService;

    /**
     * 校验数据
     *
     * @param department
     * @param add      对创建的数据进行校验
     */
    @Override
    public void validDepartment(Department department, boolean add) {
        ThrowUtils.throwIf(department == null, ErrorCode.PARAMS_ERROR);
        String name = department.getName();
        String email = department.getEmail();
        String manager = department.getManager();
        String phone = department.getPhone();
        // 创建数据时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(name,email,manager,phone), ErrorCode.PARAMS_ERROR);
        }
    }

    /**
     * 获取查询条件
     *
     * @param departmentQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<Department> getQueryWrapper(DepartmentQueryRequest departmentQueryRequest) {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        if (departmentQueryRequest == null) {
            return queryWrapper;
        }
        String name = departmentQueryRequest.getName();
        String manager = departmentQueryRequest.getManager();
        queryWrapper.eq(StringUtils.isNotBlank(name), "name", name);
        queryWrapper.eq(StringUtils.isNotBlank(manager), "manager" , manager);
        return queryWrapper;
    }

    /**
     * 获取院系封装
     *
     * @param department
     * @param request
     * @return
     */
    @Override
    public DepartmentVO getDepartmentVO(Department department, HttpServletRequest request) {
        return DepartmentVO.objToVo(department);
    }

    /**
     * 分页获取院系封装
     *
     * @param departmentPage
     * @param request
     * @return
     */
    @Override
    public Page<DepartmentVO> getDepartmentVOPage(Page<Department> departmentPage, HttpServletRequest request) {
        List<Department> departmentList = departmentPage.getRecords();
        Page<DepartmentVO> departmentVOPage = new Page<>(departmentPage.getCurrent(), departmentPage.getSize(), departmentPage.getTotal());
        if (CollUtil.isEmpty(departmentList)) {
            return departmentVOPage;
        }
        // 对象列表 => 封装对象列表
        List<DepartmentVO> departmentVOList = departmentList.stream().map(DepartmentVO::objToVo).collect(Collectors.toList());

        departmentVOPage.setRecords(departmentVOList);
        return departmentVOPage;
    }

}
