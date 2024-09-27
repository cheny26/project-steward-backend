package com.cheny.projectsteward.service;

import com.cheny.projectsteward.model.dto.teacher.TeacherRegisterRequest;
import com.cheny.projectsteward.model.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author chen
* @description 针对表【teacher(教师表)】的数据库操作Service
* @createDate 2024-09-13 23:52:07
*/
public interface TeacherService extends IService<Teacher> {
    /**
     * @param teacherRegisterRequest  teacherRegisterRequest
     * @return 新教师 id
     */
    long register(TeacherRegisterRequest teacherRegisterRequest);

    /**
     * @param employeeId 工号
     * @param password  密码
     * @return 脱敏后的教师信息
     */
    Teacher login(String employeeId, String password);

    /**
     *
     * @param employeeId 工号
     * @return boolean
     */
    boolean isAdmin(String employeeId);
}
