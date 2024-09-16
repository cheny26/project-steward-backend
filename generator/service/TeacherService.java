package com.cheny.projectSteward.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cheny.projectSteward.model.dto.teacher.TeacherQueryRequest;
import com.cheny.projectSteward.model.entity.Teacher;
import com.cheny.projectSteward.model.vo.TeacherVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 教师服务
 *
 */
public interface TeacherService extends IService<Teacher> {

    /**
     * 校验数据
     *
     * @param teacher
     * @param add 对创建的数据进行校验
     */
    void validTeacher(Teacher teacher, boolean add);

    /**
     * 获取查询条件
     *
     * @param teacherQueryRequest
     * @return
     */
    QueryWrapper<Teacher> getQueryWrapper(TeacherQueryRequest teacherQueryRequest);
    
    /**
     * 获取教师封装
     *
     * @param teacher
     * @param request
     * @return
     */
    TeacherVO getTeacherVO(Teacher teacher, HttpServletRequest request);

    /**
     * 分页获取教师封装
     *
     * @param teacherPage
     * @param request
     * @return
     */
    Page<TeacherVO> getTeacherVOPage(Page<Teacher> teacherPage, HttpServletRequest request);
}
