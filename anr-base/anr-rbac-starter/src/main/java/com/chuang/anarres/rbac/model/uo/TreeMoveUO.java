package com.chuang.anarres.rbac.model.uo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("树节点移动请求")
public class TreeMoveUO {
    @ApiModelProperty(value = "被移动的节点唯一编号", required = true)
    @NotNull
    private Integer from;
    @ApiModelProperty(value = "目的节点唯一编号", required = true)
    @NotNull
    private Integer to;
    @ApiModelProperty(value = "在目的节点的位置，小于0表示上面，等于0表示内部，大于0表示下面", required = true)
    @NotNull
    private Integer pos;
}
