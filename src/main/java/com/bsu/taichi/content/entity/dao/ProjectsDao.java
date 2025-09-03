package com.bsu.taichi.content.entity.dao;

import com.bsu.taichi.content.entity.dbo.Projects;
import com.bsu.taichi.mapper.ProjectsMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author khran
 * @version 1.0
 * @description
 */

@Component
public class ProjectsDao {

    @Autowired
    private ProjectsMapper projectsMapper;


}
