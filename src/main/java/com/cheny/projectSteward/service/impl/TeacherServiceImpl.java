package com.cheny.projectSteward.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheny.projectSteward.model.entity.Teacher;
import com.cheny.projectSteward.service.TeacherService;
import com.cheny.projectSteward.mapper.TeacherMapper;
import org.springframework.stereotype.Service;

/**
* @author chen
* @description 针对表【teacher(教师表)】的数据库操作Service实现
* @createDate 2024-09-13 23:52:07
*/
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService{

}




