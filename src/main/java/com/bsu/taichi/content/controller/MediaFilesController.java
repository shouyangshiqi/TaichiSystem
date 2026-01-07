package com.bsu.taichi.content.controller;

import com.bsu.taichi.base.model.RestResponse;
import com.bsu.taichi.content.entity.dbo.Images;
import com.bsu.taichi.content.entity.dbo.ModelParameters;
import com.bsu.taichi.content.entity.dbo.Models;
import com.bsu.taichi.content.entity.vo.CarouselImage;
import com.bsu.taichi.content.entity.vo.CategorieImage;
import com.bsu.taichi.content.entity.vo.ModelInfo;
import com.bsu.taichi.content.entity.vo.UploadFileRequest;
import com.bsu.taichi.content.service.MediaFilesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author khran
 * @version 1.0
 * @description
 */

@Slf4j
@RestController
@RequestMapping("/minio")
public class MediaFilesController {

    @Autowired
    private MediaFilesService mediaFilesService;

    /**
     * 上传文件
     *
     * @return 上传结果
     */
    @PostMapping("/upload")
    public RestResponse<Map<String, Object>> uploadFile( @RequestPart("file") MultipartFile file,
                                                         @RequestParam(value = "projectName", required = false) String projectName,
                                                         @RequestParam(value = "modelName", required = false) String modelName,
                                                         @RequestParam(value = "modelIndex", required = false) Integer modelIndex,
                                                         @RequestParam(value = "fileType", required = false) Integer fileType,
                                                         @RequestParam(value = "description", required = false) String description,
                                                         @RequestParam(value = "positionX", required = false) Float positionX,
                                                         @RequestParam(value = "positionY", required = false) Float positionY,
                                                         @RequestParam(value = "positionZ", required = false) Float positionZ,
                                                         @RequestParam(value = "rotationX", required = false) Float rotationX,
                                                         @RequestParam(value = "rotationY", required = false) Float rotationY,
                                                         @RequestParam(value = "rotationZ", required = false) Float rotationZ,
                                                         @RequestParam(value = "rotationW", required = false) Float rotationW,
                                                         @RequestParam(value = "scaleX", required = false) Float scaleX,
                                                         @RequestParam(value = "scaleY", required = false) Float scaleY,
                                                         @RequestParam(value = "scaleZ", required = false) Float scaleZ
                                                         ){

        UploadFileRequest request = new UploadFileRequest();
        request.setFile(file);
        request.setProjectName(projectName);
        request.setModelName(modelName);
        request.setModelIndex(modelIndex);
        request.setFileType(fileType);
        request.setDescription(description);
        if(fileType == 0){
            ModelParameters modelParameters = new ModelParameters();
            modelParameters.setPositionX(positionX != null ? positionX.doubleValue() : null);
            modelParameters.setPositionY(positionY != null ? positionY.doubleValue() : null);
            modelParameters.setPositionZ(positionZ != null ? positionZ.doubleValue() : null);
            modelParameters.setRotationX(rotationX != null ? rotationX.doubleValue() : null);
            modelParameters.setRotationY(rotationY != null ? rotationY.doubleValue() : null);
            modelParameters.setRotationZ(rotationZ != null ? rotationZ.doubleValue() : null);
            modelParameters.setRotationW(rotationW != null ? rotationW.doubleValue() : null);
            modelParameters.setScaleX(scaleX != null ? scaleX.doubleValue() : 1);
            modelParameters.setScaleY(scaleY != null ? scaleY.doubleValue() : 1);
            modelParameters.setScaleZ(scaleZ != null ? scaleZ.doubleValue() : 1);
            request.setModelParameters(modelParameters);
        }

        return RestResponse.success(mediaFilesService.uploadFile(request));
    }


    /**
     * 根据项目名称获取该项目的全部模型
     *
     * @param projectName 项目名称
     * @return 模型列表
     */
    @PostMapping("/models")
    public RestResponse<List<Models>> getModelsByProjectName(@RequestBody String projectName) {
        // 调用 service 层方法获取模型列表
        List<Models> models = mediaFilesService.getModelsByProjectName(projectName);
        return RestResponse.success(models);
    }

    /**
     * 获取轮播图片数据
     */
    @PostMapping("/getCarouselImages")
    public RestResponse<List<CarouselImage>> getCarouselImages() {
        List<CarouselImage> images = mediaFilesService.getCarouselImages();
        return RestResponse.success(images);
    }

    /**
     * 获取分类信息
     */
    @PostMapping("/getCategories")
    public RestResponse<List<CategorieImage>> getCategories() {
        List<CategorieImage> categories = mediaFilesService.getCategories();
        return RestResponse.success(categories);
    }


    /**
     *  获取展厅中的 图像和模型数据
     * @return
     */
    @GetMapping("exhibition/data")
    public RestResponse<List<ModelInfo>> getExhibitionData(@RequestParam("itemId") Integer projectId) {
        // Your logic here
        List<ModelInfo> modelInfos = mediaFilesService.getExhibitionData(projectId);
        return RestResponse.success(modelInfos);
    }




}
