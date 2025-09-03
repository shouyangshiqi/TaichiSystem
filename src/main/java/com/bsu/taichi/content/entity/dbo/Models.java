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
* 模型信息表
* @TableName models
*/

@Data
public class Models implements Serializable {

    /**
    * 模型编号
    */
    @ApiModelProperty("模型编号")
    private Integer modelId;
    /**
    * 项目编号
    */
    @ApiModelProperty("项目编号")
    private Integer projectId;
    /**
    * 模型名称
    */
    @NotBlank(message="[模型名称]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("模型名称")
    @Length(max= 255,message="编码长度不能超过255")
    private String modelName;
    /**
    * 模型类型
    */
    @NotBlank(message="[模型类型]不能为空")
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("模型类型")
    @Length(max= 100,message="编码长度不能超过100")
    private String modelType;
    /**
    * 模型在对象存储中的唯一键值
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("模型在对象存储中的唯一键值")
    @Length(max= 255,message="编码长度不能超过255")
    private String modelObjectKey;
    /**
    * 上传时间
    */
    @ApiModelProperty("上传时间")
    private Date createTime;


}
