# 数据库初始化
-- 创建库
create database if not exists project_steward;

-- 切换库
use project_steward;

-- 学生表 (STUDENT)
CREATE TABLE `student` (
   `id`            bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
   `student_id`    varchar(50)     NOT NULL                COMMENT '学号',
   `name`          varchar(100)    NOT NULL                COMMENT '姓名',
   `grade`         int             NOT NULL                COMMENT '年级',
   `department_id` bigint unsigned NOT NULL                COMMENT '学院ID，外键关联院系表',
   `major`         varchar(100)    NOT NULL                COMMENT '专业',
   `phone`         varchar(20)              DEFAULT NULL   COMMENT '电话',
   `email`         varchar(100)             DEFAULT NULL   COMMENT '邮箱',
   `password`      varchar(255)    NOT NULL                COMMENT '密码',
   `avatar`        varchar(255)             DEFAULT NULL   COMMENT '头像URL',
   `create_time`   datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP        COMMENT '创建时间',
   `update_time`   datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   `is_deleted`    tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记，0-未删除，1-已删除',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uk_student_id` (`student_id`),
   KEY `fk_student_department` (`department_id`)
) COMMENT='学生表';

-- 教师表 (TEACHER)
CREATE TABLE `teacher` (
   `id`            bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
   `employee_id`   varchar(50)     NOT NULL                COMMENT '工号，唯一键',
   `name`          varchar(100)    NOT NULL                COMMENT '姓名',
   `title`         varchar(50)              DEFAULT NULL   COMMENT '职称',
   `department_id` bigint unsigned NOT NULL                COMMENT '学院ID，外键关联院系表',
   `phone`         varchar(20)              DEFAULT NULL   COMMENT '电话',
   `email`         varchar(100)             DEFAULT NULL   COMMENT '邮箱',
   `password`      varchar(255)    NOT NULL                COMMENT '密码',
   `role`          tinyint unsigned NOT NULL               COMMENT '角色：0普通教师 1管理员',
   `avatar`        varchar(255)             DEFAULT NULL   COMMENT '头像URL',
   `create_time`   datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP        COMMENT '创建时间',
   `update_time`   datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   `is_deleted`    tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记，0-未删除，1-已删除',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uk_employee_id` (`employee_id`),
   KEY `fk_teacher_department` (`department_id`)
) COMMENT='教师表';

-- 院系表 (DEPARTMENT)
CREATE TABLE `department` (
  `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name`        varchar(100)    NOT NULL                COMMENT '院系名称',
  `manager`     varchar(100)             DEFAULT NULL   COMMENT '负责人',
  `phone`       varchar(20)              DEFAULT NULL   COMMENT '联系电话',
  `email`         varchar(100)             DEFAULT NULL   COMMENT '邮箱',
  `create_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP        COMMENT '创建时间',
  `update_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted`  tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记，0-未删除，1-已删除',
  PRIMARY KEY (`id`)
) COMMENT='院系表';

-- 项目表 (PROJECT)
CREATE TABLE `project` (
   `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
   `logo`        varchar(255)             DEFAULT NULL   COMMENT '项目logo URL',
   `name`        varchar(255)    NOT NULL                COMMENT '项目名称',
   `field`       varchar(100)    NOT NULL                COMMENT '所属领域',
   `description` text            NOT NULL                COMMENT '项目概述',
   `progress`    text                     DEFAULT NULL   COMMENT '项目进展',
   `plan_file`   varchar(255)             DEFAULT NULL   COMMENT '项目计划书文件URL',
   `status`      tinyint unsigned NOT NULL               COMMENT '项目状态',
   `leader_id`   bigint unsigned NOT NULL                COMMENT '项目负责人ID，外键关联学生表',
   `create_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP        COMMENT '创建时间',
   `update_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   `is_deleted`  tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记，0-未删除，1-已删除',
   PRIMARY KEY (`id`),
   KEY `fk_project_leader` (`leader_id`)
) COMMENT='项目表';

-- 项目成员表 (PROJECT_MEMBER)
CREATE TABLE `project_member` (
  `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_id`  bigint unsigned NOT NULL                COMMENT '项目ID，外键关联项目表',
  `user_id`     bigint unsigned NOT NULL                COMMENT '用户ID，外键关联学生表',
  `create_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP        COMMENT '创建时间',
  `update_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted`  tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记，0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_project_user` (`project_id`, `user_id`),
  KEY `fk_member_project` (`project_id`),
  KEY `fk_member_user` (`user_id`)
) COMMENT='项目成员表';

-- 项目导师表 (PROJECT_TUTOR)
CREATE TABLE `project_tutor` (
     `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
     `project_id`  bigint unsigned NOT NULL                COMMENT '项目ID，外键关联项目表',
     `tutor_id`    bigint unsigned NOT NULL                COMMENT '导师ID，外键关联教师表',
     `create_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP        COMMENT '创建时间',
     `update_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     `is_deleted`  tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记，0-未删除，1-已删除',
     PRIMARY KEY (`id`),
     UNIQUE KEY `uk_project_tutor` (`project_id`, `tutor_id`),
     KEY `fk_tutor_project` (`project_id`),
     KEY `fk_tutor_teacher` (`tutor_id`)
) COMMENT='项目导师表';

-- 项目评分表 (PROJECT_SCORE)
CREATE TABLE `project_score` (
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `project_id`  bigint unsigned NOT NULL                COMMENT '项目ID，外键关联项目表',
    `tutor_id`    bigint unsigned NOT NULL                COMMENT '导师ID，外键关联教师表',
    `score`       int                      DEFAULT NULL   COMMENT '评分',
    `comment`     text                     DEFAULT NULL   COMMENT '评语',
    `create_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP        COMMENT '创建时间',
    `update_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记，0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    KEY `fk_score_project` (`project_id`),
    KEY `fk_score_tutor` (`tutor_id`)
) COMMENT='项目评分表';

-- 公告表 (ANNOUNCEMENT)
CREATE TABLE `announcement` (
    `id`           bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `title`        varchar(255)    NOT NULL                COMMENT '标题',
    `content`      text            NOT NULL                COMMENT '内容',
    `author_id`    bigint unsigned NOT NULL                COMMENT '发布者ID，外键关联教师表',
    `target_role`  tinyint unsigned NOT NULL               COMMENT '目标角色：0所有用户，1学生，2教师，3管理员',
    `status`       tinyint unsigned NOT NULL               COMMENT '状态：0草稿，1已发布，2已撤回',
    `publish_date` datetime                 DEFAULT NULL   COMMENT '发布时间',
    `create_time`  datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP        COMMENT '创建时间',
    `update_time`  datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`   tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记，0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    KEY `fk_announcement_author` (`author_id`)
) COMMENT='公告表';

-- 教师绩效表 (TEACHER_PERFORMANCE)
CREATE TABLE `teacher_performance` (
   `id`                bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
   `teacher_id`        bigint unsigned NOT NULL                COMMENT '教师ID，外键关联教师表',
   `reviewed_projects` int unsigned    NOT NULL                COMMENT '评审项目数量',
   `total_score`       int unsigned    NOT NULL                COMMENT '总评分',
   `create_time`       datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP        COMMENT '创建时间',
   `update_time`       datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   `is_deleted`        tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记，0-未删除，1-已删除',
   PRIMARY KEY (`id`),
   KEY `fk_performance_teacher` (`teacher_id`)
) COMMENT='教师绩效表';
