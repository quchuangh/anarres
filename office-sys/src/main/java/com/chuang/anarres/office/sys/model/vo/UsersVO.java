package com.chuang.anarres.office.sys.model.vo;

import com.chuang.anarres.office.sys.model.bo.TreeMenuBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "用户相关信息")
public class UsersVO {
    @ApiModelProperty("用户")
    private ShiroUser user;
    @ApiModelProperty("菜单")
    private List<TreeMenuBO> menu;
    @ApiModelProperty("供客户端ACL用的角色点")
    private List<String> roles;
    @ApiModelProperty("供客户端ACL用的权限点")
    private List<String> abilities;
}
