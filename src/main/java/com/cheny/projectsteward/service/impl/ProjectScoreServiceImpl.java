package com.cheny.projectsteward.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheny.projectsteward.model.entity.ProjectScore;
import com.cheny.projectsteward.service.ProjectScoreService;
import com.cheny.projectsteward.mapper.ProjectScoreMapper;
import org.springframework.stereotype.Service;

/**
* @author chen
* @description 针对表【project_score(项目评分表)】的数据库操作Service实现
* @createDate 2024-09-13 23:52:07
*/
@Service
public class ProjectScoreServiceImpl extends ServiceImpl<ProjectScoreMapper, ProjectScore>
    implements ProjectScoreService{

}




