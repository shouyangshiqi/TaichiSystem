package com.bsu.taichi.content.entity.dbo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
* 图片信息表
* @TableName images
*/

@Data
@TableName("images")
public class Images implements Serializable {

    /**
    * 图片编号
    */
    @ApiModelProperty("图片编号")
    @TableId(type = IdType.AUTO)
    private Integer imageId;
    /**
    * 模型编号
    */
    @ApiModelProperty("模型编号")
    private Integer modelId;
    /**
    * 图片名称
    */
    @ApiModelProperty("图片名称")
    private String imageName;

    @ApiModelProperty("图片描述")
    private String imageDescription;
    /**
    * 图片类型
    */
    @ApiModelProperty("图片类型: 0: 普通图； 1： 展示图； 2：轮播图")
    private int imageType;
    /**
    * 图片在对象存储中的唯一键值
    */
    @ApiModelProperty("图片在对象存储中的唯一键值")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}
