package com.bsu.taichi.content.controller;

import com.bsu.taichi.content.entity.dao.ModelParametersDao;
import com.bsu.taichi.content.entity.dao.ModelsDao;
import com.bsu.taichi.content.entity.dbo.ModelParameters;
import com.bsu.taichi.content.entity.dbo.Models;
import com.bsu.taichi.content.entity.vo.ModelInfo;
import com.bsu.taichi.content.service.MediaFilesService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 * @author khran
 * @version 1.0
 * @description
 */

@ExtendWith(MockitoExtension.class) // 使用 JUnit 5 + Mockito 扩展
public class MediaFilesServiceTest {

    @InjectMocks
    private MediaFilesService mediaFilesService; // 注入 Mock 对象的被测类

    @Mock
    private ModelsDao modelsDao; // 模拟 ModelsDao

    @Mock
    private ModelParametersDao modelParametersDao; // 模拟 ModelParametersDao

    @Test
    @DisplayName("测试获取展厅数据 - 成功组装模型与参数")
    public void testGetExhibitionData_Success() {
        // 1. 准备测试数据 (Given)
        Integer projectId = 101;
        Integer modelId = 1;

        // 模拟 Models 数据
        Models mockModel = new Models();
        mockModel.setModelId(modelId);
        mockModel.setProjectId(projectId);
        mockModel.setModelName("Taichi_Kick");
        mockModel.setModelObjectKey("models/kick.ply");

        // 模拟 ModelParameters 数据
        ModelParameters mockParams = new ModelParameters();
        mockParams.setModelId(modelId);
        mockParams.setPositionX(10.5);
        mockParams.setRotationY(90.0);
        mockParams.setScaleZ(1.0);

        // 2. 模拟 DAO 行为 (When)
        when(modelsDao.selectByProjectId(eq(projectId)))
                .thenReturn(Collections.singletonList(mockModel));

        when(modelParametersDao.selectBatchIds(anyList()))
                .thenReturn(Collections.singletonList(mockParams));

        // 3. 执行被测方法 (Action)
        List<ModelInfo> result = mediaFilesService.getExhibitionData(projectId);

        // 4. 验证结果 (Then)
        assertNotNull(result);
        assertEquals(1, result.size());

        ModelInfo info = result.get(0);

        // 验证来自 Models 的数据是否拷贝成功
        assertEquals("models/kick.ply", info.getModelObjectKey());

        // 验证来自 ModelParameters 的数据是否拷贝成功
        assertEquals(10.5, info.getPositionX());
        assertEquals(90.0, info.getRotationY());
        assertEquals(1.0, info.getScaleZ());

        // 验证 DAO 方法是否被正确调用
        verify(modelsDao).selectByProjectId(projectId);
        verify(modelParametersDao).selectBatchIds(anyList());
    }
}
