package com.chuang.anarres.office.sys.model;

import com.chuang.anarres.enums.Language;
import com.chuang.anarres.enums.UserStatus;
import com.chuang.tauceti.support.enums.Gender;
import com.chuang.tauceti.tools.basic.collection.CollectionKit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Data
@ApiModel(description = "用户信息")
public class ShiroUser {

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("状态")
    private UserStatus state;

    @ApiModelProperty("登录次数")
    private Integer loginTimes;

    @ApiModelProperty("最后登录时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty("最后登录IP")
    private String lastLoginIp;

    @ApiModelProperty("登录成功次数")
    private Integer loginSuccessTimes;
    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("生日")
    private LocalDate birthday;

    @ApiModelProperty("性别")
    private Gender gender;

    @ApiModelProperty("语言")
    private Language language;

    @ApiModelProperty("绑定IP")
    private String ipBound;

    @ApiModelProperty("绑定MAC")
    private String macBound;

    @ApiModelProperty("创建人")
    private String creator;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新人")
    private String updater;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty("供客户端ACL用的角色点")
    private List<String> roles = new ArrayList<>();
    @ApiModelProperty("供客户端ACL用的权限点")
    private List<String> abilities = new ArrayList<>();


    public boolean canAll(Collection<String> roles, Collection<String> abilities) {
        boolean canRole = this.roles.containsAll(roles);
        boolean canAbility = this.abilities.containsAll(abilities);
        return canRole && canAbility;
    }
    public boolean canAny(Collection<String> roles, Collection<String> abilities) {
        boolean canRole = roles.stream().anyMatch(this.roles::contains);
        boolean canAbility = abilities.stream().anyMatch(this.abilities::contains);
        return canRole && canAbility;
    }
}
