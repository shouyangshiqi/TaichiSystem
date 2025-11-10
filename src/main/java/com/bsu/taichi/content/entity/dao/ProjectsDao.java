package com.bsu.taichi.content.entity.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bsu.taichi.content.entity.dbo.Projects;
import com.bsu.taichi.mapper.ProjectsMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author khran
 * @version 1.0
 * @description
 */

@Component
public class ProjectsDao {

    @Autowired
    private ProjectsMapper projectsMapper;


    public Projects selectByModelName(String projectName) {
        LambdaQueryWrapper<Projects> queryWrapper = new LambdaQueryWrapper<Projects>().eq(Projects::getProjectName, projectName);
        return projectsMapper.selectOne(queryWrapper);
    }

    public void insertOrUpdate(Projects project) {
        if (project.getProjectId() == null) {
            projectsMapper.insert(project);
        } else {
            projectsMapper.updateById(project);
        }
    }

    public List<Projects> selectAllProjectImages() {
        LambdaQueryWrapper<Projects> queryWrapper = new LambdaQueryWrapper<Projects>()
                .eq(Projects::getValidType, 0)
                .ne(Projects::getProjectId, 0);
        return projectsMapper.selectList(queryWrapper);
    }
}
