package com.bsu.taichi.content.entity.vo;

import com.bsu.taichi.content.entity.dbo.ModelParameters;
import com.bsu.taichi.content.entity.dbo.Models;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author khran
 * @version 1.0
 * @description
 */

@Data
public class ModelInfo extends ModelParameters {
    @ApiModelProperty("项目编号")
    private Integer projectId;

    @ApiModelProperty("模型序号")
    private int modelIndex;

    @ApiModelProperty("模型在对象存储中的唯一键值")
    private String modelObjectKey;

    private Double positionX;

    private Double positionY;

    private Double positionZ;

    private Double rotationX;

    private Double rotationY;

    private Double rotationZ;

    private Double rotationW;

    private Double scaleX;

    private Double scaleY;

    private Double scaleZ;

}
