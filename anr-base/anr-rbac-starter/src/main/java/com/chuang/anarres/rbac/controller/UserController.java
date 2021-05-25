package com.chuang.anarres.rbac.controller;

import com.chuang.anarres.rbac.controller.basic.ICrudController;
import com.chuang.anarres.crud.entity.User;
import com.chuang.anarres.rbac.model.co.ResetAppointmentCO;
import com.chuang.anarres.rbac.model.co.ResetJoinGroupCO;
import com.chuang.anarres.rbac.model.co.UserCO;
import com.chuang.anarres.rbac.model.co.UserRoleCO;
import com.chuang.anarres.rbac.model.ro.RoleRO;
import com.chuang.anarres.rbac.model.ro.UserRO;
import com.chuang.anarres.rbac.model.uo.UserUO;
import com.chuang.anarres.crud.service.IAuthService;
import com.chuang.anarres.crud.service.IRoleService;
import com.chuang.anarres.crud.service.IUserService;
import com.chuang.tauceti.support.BiValue;
import com.chuang.tauceti.support.Result;
import com.chuang.tauceti.tools.basic.collection.CollectionKit;
import com.chuang.tauceti.tools.basic.reflect.ConvertKit;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 用户  前端控制器
 * </p>
 *
 * @author chuang
 * @since 2021-05-21
 */
@RestController
@RequestMapping("/user")
public class UserController implements ICrudController<UserCO, UserRO, UserUO, User, IUserService> {

    @Resource private IUserService service;
    @Resource private IAuthService authService;
    @Resource private IRoleService roleService;


    @Override
    @RequiresPermissions("user:create")
    @PostMapping(value = "/create")
    @ApiOperation("创建一条数据")
    public Result<?> create(@RequestBody @ApiParam @Valid UserCO co ) {
        Optional<String> opt = authService.createUser(co.getUsername(), co.getName(), co.getIpBound(), co.getMacBound());
        if(opt.isPresent()) {
            return Result.success(opt.get());
        } else {
            return Result.fail("创建失败");
        }
    }

    @RequiresPermissions("user:change-pwd")
    @PostMapping(value = "/change-pwd/{username}/{oldPassword}/{newPassword}")
    @ApiOperation("修改密码")
    public Result<Boolean> changePassword(@PathVariable @Valid @NotEmpty String username,
                                          @PathVariable @Valid @NotEmpty String oldPassword,
                                          @PathVariable @Valid @NotEmpty String newPassword) {
        return Result.whether(authService.changePassword(username, oldPassword, newPassword));
    }

    @RequiresPermissions("user:force-change-pwd")
    @PostMapping(value = "/change-pwd/force/{username}/{newPassword}")
    @ApiOperation("强制修改密码")
    public Result<Boolean> forceChangePassword(@PathVariable @Valid @NotEmpty String username,
                                               @PathVariable @Valid @NotEmpty String newPassword) {
        return Result.whether(authService.forceChangePassword(username, newPassword));
    }

    @RequiresPermissions("user:assign-role")
    @PostMapping(value = "/assign/role")
    @ApiOperation("给用户设置角色")
    public Result<Boolean> userRoles(@RequestBody @ApiParam @Valid UserRoleCO co) {
        return Result.whether(service.assignRole(co.getUsername(), co.getRoles()));
    }

    @RequiresPermissions("user:assign-role")
    @GetMapping(value = "/roles-info")
    @ApiOperation("获取角色信息")
    public Result<BiValue<List<RoleRO>, List<RoleRO>>> userRoles(String username) {
        return Result.success(new BiValue<>(
                ConvertKit.toBeans(roleService.findUserRoles(), RoleRO::new),
                ConvertKit.toBeans(roleService.findUserRoles(username), RoleRO::new)
        ));
    }

    @RequiresPermissions("user:appointment")
    @PostMapping(value = "/appointment/reset")
    @ApiOperation("任命")
    public Result<Void> appointment(@RequestBody @ApiParam @Valid ResetAppointmentCO co) {
        return Result.whether(service.resetAppointment(co.getUsername(), CollectionKit.nullToEmpty(co.getPositionCodes())));
    }


    @RequiresPermissions("user:join-group")
    @PostMapping(value = "/join-group/reset")
    @ApiOperation("重置用户加入的组织")
    public Result<Void> resetJoinGroup(@RequestBody @ApiParam @Valid ResetJoinGroupCO co) {
        return Result.whether(service.resetJoinGroup(co.getUsername(), CollectionKit.nullToEmpty(co.getOrganizationCodes())));
    }


    @Override
    public IUserService service() {
        return service;
    }

    @Override
    public String basePermission() {
        return "user:";
    }

}

