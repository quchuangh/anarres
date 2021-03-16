package com.chuang.anarres.office.sys.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TreeVO {
    @ApiModelProperty("节点ID")
    private Integer id;
    @ApiModelProperty("父节点ID")
    private Integer parentId;
}
