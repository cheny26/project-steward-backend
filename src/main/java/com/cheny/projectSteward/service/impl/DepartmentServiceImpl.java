package com.cheny.projectSteward.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheny.projectSteward.model.entity.Department;
import com.cheny.projectSteward.service.DepartmentService;
import com.cheny.projectSteward.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

/**
* @author chen
* @description 针对表【department(院系表)】的数据库操作Service实现
* @createDate 2024-09-13 23:52:07
*/
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
    implements DepartmentService{

}




