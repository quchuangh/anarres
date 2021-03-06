package com.chuang.anarres.rbac.controller;

import com.chuang.anarres.rbac.OfficeUtils;
import com.chuang.anarres.rbac.controller.basic.ICrudController;
import com.chuang.anarres.crud.entity.Role;
import com.chuang.anarres.rbac.model.co.RoleAssignCO;
import com.chuang.anarres.rbac.model.co.RoleCO;
import com.chuang.anarres.rbac.model.ro.FullRoleAbilityRO;
import com.chuang.anarres.rbac.model.ro.LicensableAbilityRO;
import com.chuang.anarres.rbac.model.ro.RoleRO;
import com.chuang.anarres.rbac.model.uo.RoleUO;
import com.chuang.anarres.crud.service.IAbilityService;
import com.chuang.anarres.crud.service.IAssignService;
import com.chuang.anarres.crud.service.IRoleService;
import com.chuang.tauceti.support.Result;
import com.chuang.tauceti.tools.basic.reflect.ConvertKit;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表;角色 前端控制器
 * </p>
 *
 * @author chuang
 * @since 2021-05-07
 */
@RestController
@RequestMapping("/role")
public class RoleController implements ICrudController<RoleCO, RoleRO, RoleUO, Role, IRoleService> {

    @Resource private IRoleService service;
    @Resource private IAbilityService abilityService;
    @Resource private IAssignService assignService;

    @RequiresPermissions("ability:query")
    @GetMapping("/{roleId}/abilities")
    public Result<List<LicensableAbilityRO>> abilities(@PathVariable Integer roleId) {
        return Result.success(abilityService.findByRoleId(roleId))
                .map(abilities -> ConvertKit.convert(abilities, ArrayList::new, LicensableAbilityRO::new));
    }

    @RequiresPermissions(value = {"ability:query", "role:assign"}, logical = Logical.OR)
    @GetMapping("/{roleId}/abilities/full")
    public Result<List<FullRoleAbilityRO>> fullRoleAbilities(@PathVariable @Valid Integer roleId) {
        return Result.success(assignService.fullRoleAbilities(OfficeUtils.shiroUserNotNull().getUsername(), roleId))
                .map(roleAbilityBOS -> ConvertKit.toBeans(roleAbilityBOS, FullRoleAbilityRO::new));
    }




    @RequiresPermissions("role:assign")
    @PostMapping("/assign")
    public Result<Boolean> assign(@RequestBody @ApiParam @Valid RoleAssignCO roleAssign) {
        return Result.success(
                assignService.assign(
                        OfficeUtils.shiroUserNotNull().getUsername(),
                        roleAssign.getRoleId(),
                        roleAssign.getAbilities()
                )
        );
    }

    @Override
    public IRoleService service() {
        return service;
    }


    @Override
    public String basePermission() {
        return "role:";
    }

}

