package com.cheny.projectSteward.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheny.projectSteward.model.entity.Student;
import com.cheny.projectSteward.service.StudentService;
import com.cheny.projectSteward.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author chen
* @description 针对表【student(学生表)】的数据库操作Service实现
* @createDate 2024-09-13 23:52:07
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}




