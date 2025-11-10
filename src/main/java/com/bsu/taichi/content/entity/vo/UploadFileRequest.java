package com.bsu.taichi.content.entity.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author khran
 * @version 1.0
 * @description
 */

@Data
public class UploadFileRequest {

    @ApiModelProperty("文件")
    private MultipartFile file;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("模型名称")
    private String modelName;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("模型序号")
    private int modelIndex;

    @ApiModelProperty("文件类型：0: 模型， 1： 图片")
    private Integer fileType;

}
