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
* 项目信息表
* @TableName projects
*/
@Data
public class Projects implements Serializable {

    /**
    * 项目编号
    */
    @ApiModelProperty("项目编号")
    private Integer projectId;
    /**
    * 项目名称
    */
    @NotBlank(message="[项目名称]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("项目名称")
    @Length(max= 255,message="编码长度不能超过255")
    private String projectName;

    /**
    * 项目描述
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("项目描述")
    @Length(max= 255,message="编码长度不能超过255")
    private String projectDescription;
    /**
    * 项目标号
    */
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("项目标号")
    @Length(max= 100,message="编码长度不能超过100")
    private String projectMark;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
    * 更新时间
    */
    @ApiModelProperty("更新时间")
    private Date updateTime;

}
