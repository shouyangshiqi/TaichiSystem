-- 创建项目表
CREATE TABLE projects (
                          project_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '项目编号',
                          project_name VARCHAR(255) NOT NULL COMMENT '项目名称',
                          project_description VARCHAR(255) COMMENT '项目描述',
                          project_mark VARCHAR(100) COMMENT '项目标号',
                          create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          INDEX idx_project_name (project_name),
                          INDEX idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='项目信息表';

-- 创建模型表
CREATE TABLE models (
                        model_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '模型编号',
                        project_id INT NOT NULL COMMENT '项目编号',
                        model_name VARCHAR(255) NOT NULL COMMENT '模型名称',
                        model_type VARCHAR(100) NOT NULL COMMENT '模型类型',
                        model_object_key VARCHAR(255) UNIQUE COMMENT '模型在对象存储中的唯一键值',
                        create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
                        INDEX idx_project_id (project_id),
                        INDEX idx_model_type (model_type),
                        FOREIGN KEY (project_id) REFERENCES projects(project_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='模型信息表';

-- 创建图片表
CREATE TABLE images (
                        image_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '图片编号',
                        model_id INT NOT NULL COMMENT '模型编号',
                        image_name VARCHAR(255) NOT NULL COMMENT '图片名称',
                        image_type VARCHAR(50) NOT NULL COMMENT '图片类型',
                        image_object_key VARCHAR(255) UNIQUE NOT NULL COMMENT '图片在对象存储中的唯一键值',
                        image_size BIGINT COMMENT '图片大小(字节)',
                        upload_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
                        INDEX idx_model_id (model_id),
                        INDEX idx_image_object_key (image_object_key),
                        FOREIGN KEY (model_id) REFERENCES models(model_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='图片信息表';

