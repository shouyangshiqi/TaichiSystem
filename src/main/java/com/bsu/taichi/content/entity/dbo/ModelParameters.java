package com.bsu.taichi.content.entity.dbo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.bouncycastle.asn1.cms.TimeStampedData;

/**
 * @author khran
 * @version 1.0
 * @description
 */


@Data
@TableName("model_parameters")
public class ModelParameters {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer modelId;

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
