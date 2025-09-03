package com.bsu.taichi.content.entity.dbo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
* 图片信息表
* @TableName images
*/

@Data
public class Images implements Serializable {

    /**
    * 图片编号
    */
    @ApiModelProperty("图片编号")
    private Integer imageId;
    /**
    * 模型编号
    */
    @ApiModelProperty("模型编号")
    private Integer modelId;
    /**
    * 图片名称
    */
    @NotBlank(message="[图片名称]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("图片名称")
    @Length(max= 255,message="编码长度不能超过255")
    private String imageName;
    /**
    * 图片类型
    */
    @NotBlank(message="[图片类型]不能为空")
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("图片类型")
    @Length(max= 50,message="编码长度不能超过50")
    private String imageType;
    /**
    * 图片在对象存储中的唯一键值
    */
    @NotBlank(message="[图片在对象存储中的唯一键值]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("图片在对象存储中的唯一键值")
    @Length(max= 255,message="编码长度不能超过255")
    private String imageObjectKey;
    /**
    * 图片大小(字节)
    */
    @ApiModelProperty("图片大小(字节)")
    private Long imageSize;
    /**
    * 上传时间
    */
    @ApiModelProperty("上传时间")
    private Date uploadTime;

}
