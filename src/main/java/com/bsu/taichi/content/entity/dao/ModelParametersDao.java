package com.bsu.taichi.content.entity.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bsu.taichi.content.entity.dbo.ModelParameters;
import com.bsu.taichi.mapper.ModelParametersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author khran
 * @version 1.0
 * @description
 */

@Component
public class ModelParametersDao {

    @Autowired
    private ModelParametersMapper modelParametersMapper;

    public void insert(ModelParameters modelParameters) {
        modelParametersMapper.insert(modelParameters);
    }

    public List<ModelParameters> selectBatchIds(List<Integer> modelIds) {
        LambdaQueryWrapper<ModelParameters> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ModelParameters::getModelId, modelIds);
        return modelParametersMapper.selectList(queryWrapper);
    }
}
