
-- 创建数据库
CREATE DATABASE IF NOT EXISTS taichi_system DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE taichi_system;

-- 用户表
CREATE TABLE `sys_user` (
                            `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                            `username` VARCHAR(50) NOT NULL COMMENT '用户名',
                            `password` VARCHAR(255) NOT NULL COMMENT '密码',
                            `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
                            `phone` VARCHAR(20) DEFAULT NULL COMMENT '手机号',
                            `nickname` VARCHAR(100) DEFAULT NULL COMMENT '昵称',
                            `avatar` VARCHAR(255) DEFAULT NULL COMMENT '头像URL',
                            `status` TINYINT NOT NULL DEFAULT '1' COMMENT '状态: 0-禁用, 1-正常',
                            `user_type` VARCHAR(20) NOT NULL DEFAULT 'USER' COMMENT '用户类型: USER-普通用户, ADMIN-管理员, SUPER_ADMIN-超级管理员',
                            `last_login_time` DATETIME DEFAULT NULL COMMENT '最后登录时间',
                            `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uk_username` (`username`),
                            UNIQUE KEY `uk_email` (`email`),
                            KEY `idx_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- 角色表
CREATE TABLE `sys_role` (
                            `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                            `role_name` VARCHAR(50) NOT NULL COMMENT '角色名称',
                            `role_code` VARCHAR(50) NOT NULL COMMENT '角色编码',
                            `description` VARCHAR(255) DEFAULT NULL COMMENT '角色描述',
                            `status` TINYINT NOT NULL DEFAULT '1' COMMENT '状态: 0-禁用, 1-正常',
                            `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uk_role_code` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';

-- 权限表
CREATE TABLE `sys_permission` (
                                  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '权限ID',
                                  `permission_name` VARCHAR(100) NOT NULL COMMENT '权限名称',
                                  `permission_code` VARCHAR(100) NOT NULL COMMENT '权限编码',
                                  `permission_type` TINYINT NOT NULL DEFAULT '1' COMMENT '权限类型: 1-菜单, 2-按钮, 3-API接口',
                                  `parent_id` BIGINT UNSIGNED DEFAULT '0' COMMENT '父权限ID',
                                  `path` VARCHAR(255) DEFAULT NULL COMMENT '路由地址',
                                  `component` VARCHAR(255) DEFAULT NULL COMMENT '组件路径',
                                  `icon` VARCHAR(50) DEFAULT NULL COMMENT '图标',
                                  `sort_order` INT DEFAULT '0' COMMENT '排序',
                                  `status` TINYINT NOT NULL DEFAULT '1' COMMENT '状态: 0-禁用, 1-正常',
                                  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `uk_permission_code` (`permission_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权限表';

-- 用户角色关联表
CREATE TABLE `sys_user_role` (
                                 `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                 `user_id` BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
                                 `role_id` BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
                                 `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `uk_user_role` (`user_id`, `role_id`),
                                 KEY `idx_user_id` (`user_id`),
                                 KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色关联表';

-- 角色权限关联表
CREATE TABLE `sys_role_permission` (
                                       `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                       `role_id` BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
                                       `permission_id` BIGINT UNSIGNED NOT NULL COMMENT '权限ID',
                                       `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       PRIMARY KEY (`id`),
                                       UNIQUE KEY `uk_role_permission` (`role_id`, `permission_id`),
                                       KEY `idx_role_id` (`role_id`),
                                       KEY `idx_permission_id` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色权限关联表';

-- 登录日志表
CREATE TABLE `sys_login_log` (
                                 `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '日志ID',
                                 `user_id` BIGINT UNSIGNED DEFAULT NULL COMMENT '用户ID',
                                 `username` VARCHAR(50) NOT NULL COMMENT '用户名',
                                 `ip_address` VARCHAR(50) DEFAULT NULL COMMENT 'IP地址',
                                 `user_agent` VARCHAR(500) DEFAULT NULL COMMENT '用户代理',
                                 `login_status` TINYINT NOT NULL DEFAULT '1' COMMENT '登录状态: 0-失败, 1-成功',
                                 `login_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间',
                                 `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
                                 PRIMARY KEY (`id`),
                                 KEY `idx_user_id` (`user_id`),
                                 KEY `idx_username` (`username`),
                                 KEY `idx_login_time` (`login_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='登录日志表';

-- 用户文件表（用于MinIO文件管理）
CREATE TABLE `user_file` (
                             `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '文件ID',
                             `user_id` BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
                             `original_name` VARCHAR(255) NOT NULL COMMENT '原始文件名',
                             `file_name` VARCHAR(255) NOT NULL COMMENT '存储文件名',
                             `file_path` VARCHAR(500) NOT NULL COMMENT '文件路径',
                             `file_size` BIGINT NOT NULL DEFAULT '0' COMMENT '文件大小(字节)',
                             `file_type` VARCHAR(50) DEFAULT NULL COMMENT '文件类型',
                             `bucket_name` VARCHAR(100) NOT NULL COMMENT '存储桶名称',
                             `object_key` VARCHAR(500) NOT NULL COMMENT '对象键',
                             `file_url` VARCHAR(1000) NOT NULL COMMENT '文件访问URL',
                             `status` TINYINT NOT NULL DEFAULT '1' COMMENT '状态: 0-已删除, 1-正常',
                             `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             KEY `idx_user_id` (`user_id`),
                             KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户文件表';

-- 3D模型表（用于ThreeJS展示）
CREATE TABLE `model_3d` (
                            `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '模型ID',
                            `user_id` BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
                            `name` VARCHAR(255) NOT NULL COMMENT '模型名称',
                            `description` TEXT DEFAULT NULL COMMENT '模型描述',
                            `thumbnail_url` VARCHAR(1000) DEFAULT NULL COMMENT '缩略图URL',
                            `model_file_id` BIGINT UNSIGNED NOT NULL COMMENT '模型文件ID',
                            `texture_file_id` BIGINT UNSIGNED DEFAULT NULL COMMENT '纹理文件ID',
                            `material_file_id` BIGINT UNSIGNED DEFAULT NULL COMMENT '材质文件ID',
                            `config` JSON DEFAULT NULL COMMENT '模型配置信息',
                            `tags` VARCHAR(500) DEFAULT NULL COMMENT '标签，逗号分隔',
                            `status` TINYINT NOT NULL DEFAULT '1' COMMENT '状态: 0-私有, 1-公开',
                            `view_count` INT NOT NULL DEFAULT '0' COMMENT '查看次数',
                            `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`),
                            KEY `idx_user_id` (`user_id`),
                            KEY `idx_create_time` (`create_time`),
                            KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='3D模型表';

-- 初始化数据
-- 插入默认角色
INSERT INTO `sys_role` (`role_name`, `role_code`, `description`) VALUES
                                                                     ('超级管理员', 'SUPER_ADMIN', '系统超级管理员'),
                                                                     ('管理员', 'ADMIN', '系统管理员'),
                                                                     ('普通用户', 'USER', '普通用户');

-- 插入默认权限
INSERT INTO `sys_permission` (`permission_name`, `permission_code`, `permission_type`, `path`, `component`) VALUES
                                                                                                                ('系统管理', 'sys:manage', 1, '/system', NULL),
                                                                                                                ('用户管理', 'sys:user:manage', 1, '/system/user', '/system/user'),
                                                                                                                ('用户查询', 'sys:user:list', 3, NULL, NULL),
                                                                                                                ('用户新增', 'sys:user:add', 2, NULL, NULL),
                                                                                                                ('用户编辑', 'sys:user:edit', 2, NULL, NULL),
                                                                                                                ('用户删除', 'sys:user:delete', 2, NULL, NULL),
                                                                                                                ('角色管理', 'sys:role:manage', 1, '/system/role', '/system/role'),
                                                                                                                ('角色查询', 'sys:role:list', 3, NULL, NULL),
                                                                                                                ('角色新增', 'sys:role:add', 2, NULL, NULL),
                                                                                                                ('角色编辑', 'sys:role:edit', 2, NULL, NULL),
                                                                                                                ('角色删除', 'sys:role:delete', 2, NULL, NULL),
                                                                                                                ('文件管理', 'sys:file:manage', 1, '/file', '/file'),
                                                                                                                ('文件查询', 'sys:file:list', 3, NULL, NULL),
                                                                                                                ('文件删除', 'sys:file:delete', 2, NULL, NULL),
                                                                                                                ('模型管理', 'model:manage', 1, '/model', '/model'),
                                                                                                                ('模型查询', 'model:list', 3, NULL, NULL),
                                                                                                                ('模型上传', 'model:upload', 2, NULL, NULL),
                                                                                                                ('模型编辑', 'model:edit', 2, NULL, NULL),
                                                                                                                ('模型删除', 'model:delete', 2, NULL, NULL);

-- 插入默认用户（密码为: admin123）
INSERT INTO `sys_user` (`username`, `password`, `email`, `nickname`, `user_type`) VALUES
    ('admin', 'admin', 'admin@example.com', '管理员', 'SUPER_ADMIN');

-- 给管理员分配角色
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES
    (1, 1);

-- 给超级管理员角色分配所有权限
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
SELECT 1, id FROM `sys_permission`;

