package com.chuang.anarres.rbac.model.ro;

import com.chuang.anarres.crud.entity.bo.TreeMenuBO;
import com.chuang.anarres.rbac.model.ShiroUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "用户相关信息")
public class UsersRO {
    @ApiModelProperty("用户")
    private ShiroUser user;
    @ApiModelProperty("菜单")
    private List<TreeMenuBO> menu;
}
