package com.cheny.projectsteward.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheny.projectsteward.model.entity.ProjectMember;
import com.cheny.projectsteward.service.ProjectMemberService;
import com.cheny.projectsteward.mapper.ProjectMemberMapper;
import org.springframework.stereotype.Service;

/**
* @author chen
* @description 针对表【project_member(项目成员表)】的数据库操作Service实现
* @createDate 2024-09-13 23:52:07
*/
@Service
public class ProjectMemberServiceImpl extends ServiceImpl<ProjectMemberMapper, ProjectMember>
    implements ProjectMemberService{

}




