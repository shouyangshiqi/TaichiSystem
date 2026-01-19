package com.bsu.taichi.content.controller;

/**
 * @author khran
 * @version 1.0
 * @description
 */

import com.bsu.taichi.auth.filter.JwtAuthenticationFilter;
import com.bsu.taichi.auth.service.UserService;
import com.bsu.taichi.content.entity.vo.ModelInfo;
import com.bsu.taichi.content.service.MediaFilesService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * MediaFilesController 单元测试类
 * 重点验证三维展厅数据获取接口的逻辑正确性
 */
@WebMvcTest(MediaFilesController.class)
// 1. 关键点：禁用 Security 过滤器链，避免 403 错误和 Mock Filter 导致的请求中断
@AutoConfigureMockMvc(addFilters = false)
public class MediaFilesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MediaFilesService mediaFilesService;

    // 2. 关键点：补全 SecurityConfig 构造函数所需的依赖 Bean，解决 Failed to load ApplicationContext
    @MockBean
    private UserService userService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    @DisplayName("测试获取三维展厅数据接口 - 成功场景")
    public void testGetExhibitionData_Success() throws Exception {
        // 1. 准备数据
        int projectId = 101;
        String expectedModelName = "Taichi_Kick_01";

        List<ModelInfo> mockModelList = new ArrayList<>();
        ModelInfo modelInfo = new ModelInfo();
        modelInfo.setProjectId(projectId);
        modelInfo.setModelIndex(1);
        modelInfo.setModelObjectKey("taichi/models/kick.ply");
        mockModelList.add(modelInfo);

        // 2. 模拟 Service 行为
        given(mediaFilesService.getExhibitionData(projectId)).willReturn(mockModelList);

        // 3. 执行与断言
        mockMvc.perform(get("/minio/exhibition/data")
                        .param("itemId", String.valueOf(projectId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.result[0].modelObjectKey").value("taichi/models/kick.ply"));
    }


}