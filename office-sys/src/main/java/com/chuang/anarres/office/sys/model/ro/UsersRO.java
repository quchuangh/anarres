package com.chuang.anarres.office.sys.model.ro;

import com.chuang.anarres.office.sys.model.bo.TreeMenuBO;
import com.chuang.anarres.office.sys.model.ShiroUser;
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
