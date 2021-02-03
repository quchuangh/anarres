package com.chuang.anarres.office.sys.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "系统信息")
public class AppVO {

    @ApiModelProperty("App名称")
    private String name;
    @ApiModelProperty("App描述")
    private String description;
}
