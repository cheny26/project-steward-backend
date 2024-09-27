package com.cheny.projectsteward.config;

import cn.dev33.satoken.stp.StpInterface;
import com.cheny.projectsteward.model.enums.TeacherRoleEnum;
import com.cheny.projectsteward.service.TeacherService;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
@Component    // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private TeacherService teacherService;

    /**
     * 返回一个账号所拥有的权限码集合 
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        return new ArrayList<String>();
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> list = new ArrayList<String>();
        list.add(TeacherRoleEnum.ADMIN.getText());
        list.add(TeacherRoleEnum.Teacher.getText());
        return list;
    }

}
