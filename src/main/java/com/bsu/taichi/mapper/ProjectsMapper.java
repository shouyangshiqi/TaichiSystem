package com.bsu.taichi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bsu.taichi.content.entity.dbo.Projects;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 10906
* @description 针对表【projects(项目信息表)】的数据库操作Mapper
* @createDate 2025-09-02 20:55:28
* @Entity com/bsu/taichi.Projects
*/

@Mapper
public interface ProjectsMapper extends BaseMapper<Projects> {

}




