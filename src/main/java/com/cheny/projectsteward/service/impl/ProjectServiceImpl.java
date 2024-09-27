package com.cheny.projectsteward.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheny.projectsteward.model.entity.Project;
import com.cheny.projectsteward.service.ProjectService;
import com.cheny.projectsteward.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

/**
* @author chen
* @description 针对表【project(项目表)】的数据库操作Service实现
* @createDate 2024-09-13 23:52:07
*/
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project>
    implements ProjectService{

}




