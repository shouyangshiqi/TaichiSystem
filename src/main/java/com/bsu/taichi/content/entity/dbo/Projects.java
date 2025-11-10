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
* 项目信息表
* @TableName projects
*/
@Data
@TableName("projects")
public class Projects implements Serializable {

    /**
    * 项目编号
    */
    @ApiModelProperty("项目编号")
    @TableId(value = "project_id", type = IdType.AUTO)
    private Integer projectId;
    /**
    * 项目名称
    */
    @ApiModelProperty("项目名称")
    private String projectName;

    /**
    * 项目描述
    */
    @ApiModelProperty("项目描述")
    private String projectDescription;
    /**
    * 项目标号
    */
    @ApiModelProperty("项目标号")
    private String projectMark;


    @ApiModelProperty("项目有效性")
    private Integer validType;

    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
    * 更新时间
    */
    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
