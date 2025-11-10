package com.bsu.taichi.content.entity.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bsu.taichi.content.entity.dbo.Models;
import com.bsu.taichi.mapper.ModelsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author khran
 * @version 1.0
 * @description
 */

@Component
public class ModelsDao {

    @Autowired
    private ModelsMapper modelsMapper;

    public Models selectByProjectAndModelIndex(int projectId, int modelIndex) {
        LambdaQueryWrapper<Models> wrapper = new LambdaQueryWrapper<Models>().eq(Models::getProjectId, projectId).eq(Models::getModelIndex, modelIndex);
        return modelsMapper.selectOne(wrapper);
    }

    public void insertOrUpdate(Models models) {
        if(models.getModelId() == null){
            modelsMapper.insert(models);
        } else {
            modelsMapper.updateById(models);
        }
    }

    public List<Models> selectByProjectId(Integer projectId) {
        LambdaQueryWrapper<Models> wrapper = new LambdaQueryWrapper<Models>().eq(Models::getProjectId, projectId);
        return modelsMapper.selectList(wrapper);
    }
}
