package com.bsu.taichi.content.service;

import com.bsu.taichi.base.execption.TaichiException;
import com.bsu.taichi.content.entity.dao.ImagesDao;
import com.bsu.taichi.content.entity.dao.ModelParametersDao;
import com.bsu.taichi.content.entity.dao.ModelsDao;
import com.bsu.taichi.content.entity.dao.ProjectsDao;
import com.bsu.taichi.content.entity.dbo.Images;
import com.bsu.taichi.content.entity.dbo.ModelParameters;
import com.bsu.taichi.content.entity.dbo.Models;
import com.bsu.taichi.content.entity.dbo.Projects;
import com.bsu.taichi.content.entity.vo.CarouselImage;
import com.bsu.taichi.content.entity.vo.CategorieImage;
import com.bsu.taichi.content.entity.vo.ModelInfo;
import com.bsu.taichi.content.entity.vo.UploadFileRequest;
import com.bsu.taichi.content.util.MinioUtils;
import io.minio.ObjectWriteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author khran
 * @version 1.0
 * @description
 */

@Service
@Slf4j
public class MediaFilesService {

    @Autowired
    private ModelParametersDao modelParametersDao;

    @Autowired
    private ModelsDao modelsDao;

    @Autowired
    private ImagesDao imagesDao;

    @Autowired
    private ProjectsDao projectsDao;

    @Autowired
    private MinioUtils minioUtils;

    @Value("${minio.bucketName}")
    private String bucketName;

    private final String MINIO_ENDPOINT = "http://localhost:9005";


    @Transactional
    public Map<String, Object> uploadFile( UploadFileRequest uploadFileRequest) {
        Map<String, Object> result = new HashMap<>();
        MultipartFile file = uploadFileRequest.getFile();
        try {
            // 生成文件名
            String fileName = file.getOriginalFilename();
            // 根据传入保存路径 + fileName 生成 objectName
            String objectName = null;
            if(uploadFileRequest.getFileType() == 0){
                // 模型文件
                objectName = uploadFileRequest.getProjectName() + "/models/" + uploadFileRequest.getModelName() + ".ply";
            }else {
                // 图片文件
                objectName = uploadFileRequest.getProjectName() + "/images/"+ uploadFileRequest.getModelName() + "/" + fileName;
            }

            // 将相关数据保存到数据库中
            // 更新项目数据库内容
            Projects project = projectsDao.selectByModelName(uploadFileRequest.getProjectName());
            if(project == null){
                project = new Projects();
                project.setProjectName(uploadFileRequest.getProjectName());
                project.setProjectDescription("");
                project.setProjectMark("");
            }
            Date now = new Date();
            project.setUpdateTime(now);
            projectsDao.insertOrUpdate(project);

            String filePath = bucketName+ "/" + objectName;

            // 更新模型数据库内容
            if(uploadFileRequest.getFileType() == 0){
                // 模型文件
                Models models = modelsDao.selectByProjectAndModelIndex(project.getProjectId(), uploadFileRequest.getModelIndex());
                if(models == null){
                    models = new Models();
                    models.setProjectId(project.getProjectId());
                    models.setModelIndex(uploadFileRequest.getModelIndex());
                    models.setModelName(uploadFileRequest.getModelName());
                    models.setModelType("");
                    models.setModelObjectKey(filePath);
                }

                models.setCreateTime(now);
                modelsDao.insertOrUpdate(models);

                // 将位置参数保存进数据表model_parameters
                ModelParameters modelParameters = uploadFileRequest.getModelParameters();
                modelParameters.setModelId(models.getModelId());
                modelParametersDao.insert(modelParameters);

            } else {
                // 图片文件
                Models models = modelsDao.selectByProjectAndModelIndex(project.getProjectId(), uploadFileRequest.getModelIndex());
                if(models == null){
                    TaichiException.cast("模型信息不存在，无法保存图片信息");
                }
                Images images = new Images();
                images.setModelId(models.getModelId());
                images.setImageName(fileName);
                images.setImageDescription(uploadFileRequest.getDescription());
                images.setImageType(1);
                images.setImageObjectKey(filePath);
                images.setImageSize(file.getSize());
                images.setCreateTime(now);
                imagesDao.insert(images);
            }

            // 上传文件
            ObjectWriteResponse response = minioUtils.uploadFile(
                    bucketName,
                    file,
                    objectName,
                    file.getContentType());

            result.put("success", true);
            result.put("objectName", objectName);
            result.put("etag", response.etag());
            result.put("versionId", response.versionId());
            return result;
        } catch (Exception e) {
            log.error("文件上传失败", e);
            TaichiException.cast("文件上传失败");
        }
        return null;
    }

    public List<Models> getModelsByProjectName(String projectName) {
        Projects project = projectsDao.selectByModelName(projectName);
        if(project == null){
            TaichiException.cast("项目不存在");
        }
        List<Models> models = modelsDao.selectByProjectId(project.getProjectId());
        return models;
    }

    public List<CarouselImage> getCarouselImages() {
        List<Images> images = imagesDao.selectByModelId(0);
        ArrayList<CarouselImage> carouselImages = new ArrayList<>();
        for (Images image : images) {
            CarouselImage carouselImage = new CarouselImage();
            carouselImage.setId(image.getImageId());
            carouselImage.setName(image.getImageName().split("\\.")[0]);
            carouselImage.setDescription(image.getImageDescription());
            carouselImage.setImage("/" + image.getImageObjectKey());
            carouselImages.add(carouselImage);
        }
        return carouselImages;
    }

    public List<CategorieImage> getCategories() {
        List<Projects> projects = projectsDao.selectAllProjectImages();
        ArrayList<CategorieImage> result = new ArrayList<>();
        for(Projects project : projects){
            CategorieImage categorieImage = new CategorieImage();
            categorieImage.setId(project.getProjectId());
            categorieImage.setName(project.getProjectName());

            // 获取分类的图片信息
            ArrayList<CarouselImage> carouselImages = new ArrayList<>();
            List<Models> models = modelsDao.selectByProjectId(project.getProjectId());
            for (Models model : models) {
                List<Images> images = imagesDao.selectByModelIdAndShow(model.getModelId());
                if(images.size() <= 0){
                    continue;
                }
                Images image = images.get(0);
                CarouselImage carouselImage = new CarouselImage();
                carouselImage.setId(image.getImageId());
                carouselImage.setName(model.getModelName());
                carouselImage.setImage("/" + image.getImageObjectKey());
                carouselImages.add(carouselImage);
            }
            categorieImage.setImages(carouselImages);
            result.add(categorieImage);
        }

        return result;

    }

    public List<ModelInfo> getExhibitionData(Integer projectId) {
        List<Models> models = modelsDao.selectByProjectId(projectId);
        // 根据model id 获取对应的model_parameters
        List<Integer> modelIds = models.stream().map(Models::getModelId).collect(Collectors.toList());
        List<ModelParameters> modelParameters = modelParametersDao.selectBatchIds(modelIds);
        HashMap<Integer, ModelParameters> map = new HashMap<>();
        for (ModelParameters parameter : modelParameters) {
            map.put(parameter.getModelId(), parameter);
        }

        // 组装对象返回数据
        ArrayList<ModelInfo> modelInfos = new ArrayList<>();
        for (Models model : models) {
            ModelInfo modelInfo = new ModelInfo();
            BeanUtils.copyProperties(model, modelInfo);
            ModelParameters orDefault = map.getOrDefault(model.getModelId(), null);
            if(orDefault != null){
                BeanUtils.copyProperties(orDefault, modelInfo);
                modelInfos.add(modelInfo);
            }
        }
        // 返回数据
        return modelInfos;
    }
}
