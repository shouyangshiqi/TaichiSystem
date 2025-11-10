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
* 模型信息表
* @TableName models
*/

@Data
@TableName("models")
public class Models implements Serializable {

    /**
    * 模型编号
    */
    @ApiModelProperty("模型编号")
    @TableId(type = IdType.AUTO)
    private Integer modelId;
    /**
    * 项目编号
    */
    @ApiModelProperty("项目编号")
    private Integer projectId;

    @ApiModelProperty("模型序号")
    private int modelIndex;
    /**
    * 模型名称
    */
    @ApiModelProperty("模型名称")
    private String modelName;
    /**
    * 模型类型
    */
    @ApiModelProperty("模型类型")
    private String modelType;
    /**
    * 模型在对象存储中的唯一键值
    */
    @ApiModelProperty("模型在对象存储中的唯一键值")
    private String modelObjectKey;
    /**
    * 上传时间
    */
    @ApiModelProperty("上传时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


}
