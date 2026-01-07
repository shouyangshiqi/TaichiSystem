package com.bsu.taichi.content.entity.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bsu.taichi.content.entity.dbo.Images;
import com.bsu.taichi.mapper.ImagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author khran
 * @version 1.0
 * @description
 */


@Component
public class ImagesDao {

    @Autowired
    private ImagesMapper imagesMapper;

    public void insert(Images images) {
        imagesMapper.insert(images);
    }

    public List<Images> selectByModelId(int id) {
        LambdaQueryWrapper<Images> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Images::getModelId, id);
        wrapper.eq(Images::getImageType, 1);
        return imagesMapper.selectList(wrapper);
    }

    public List<Images> selectByModelIdAndShow(Integer modelId) {
        LambdaQueryWrapper<Images> eq = new LambdaQueryWrapper<Images>().eq(Images::getModelId, modelId).eq(Images::getImageType, 1);
        return imagesMapper.selectList(eq);
    }
}
