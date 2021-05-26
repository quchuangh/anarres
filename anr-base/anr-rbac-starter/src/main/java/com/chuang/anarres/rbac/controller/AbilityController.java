package com.chuang.anarres.rbac.controller;


import com.chuang.anarres.crud.service.IRoleAbilityService;
import com.chuang.anarres.rbac.controller.basic.ICrudController;
import com.chuang.anarres.crud.entity.Ability;
import com.chuang.anarres.rbac.model.co.AbilityCO;
import com.chuang.anarres.rbac.model.ro.AbilityRO;
import com.chuang.anarres.rbac.model.uo.AbilityUO;
import com.chuang.anarres.rbac.model.uo.TreeMoveUO;
import com.chuang.anarres.crud.service.IAbilityService;
import com.chuang.tauceti.support.Result;
import com.chuang.tauceti.tools.basic.reflect.ConvertKit;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限表 权限 前端控制器
 * </p>
 *
 * @author chuang
 * @since 2021-01-05
 */
@RestController
@RequestMapping("/sys/ability")
public class AbilityController implements ICrudController<AbilityCO, AbilityRO, AbilityUO, Ability, IAbilityService> {

    @Resource private IAbilityService abilityService;
    @Resource private IRoleAbilityService roleAbilityService;

    @PostMapping("/move")
    @ApiOperation("移动菜单")
    public Result<Boolean> move(@RequestBody @ApiParam TreeMoveUO move) {
        return Result.success(this.abilityService.move(move.getFrom(), move.getTo(), move.getPos()));
    }

    @GetMapping("/all")
    public Result<List<AbilityRO>> all() {
        return Result.success(
                ConvertKit.toBeans(abilityService.list(), AbilityRO::new)
                        .stream()
                        .sorted(Comparator.comparing(AbilityRO::getSortRank))
                        .collect(Collectors.toList())
        );
    }

    @RequiresPermissions("ability:create")
    @PostMapping(value = "/create")
    @ApiOperation("创建一条数据")
    @ResponseBody
    public Result<?> create(@RequestBody @ApiParam @Valid AbilityCO co) {
        Ability entity = ConvertKit.toBean(co, Ability::new);
        boolean success = abilityService.save(entity);
        if(success) {
            roleAbilityService.addAdminRoleAbility(entity.getId());
            return Result.success(ConvertKit.toBean(entity, AbilityRO::new));
        } else {
            return Result.fail("");
        }
    }

    @Override
    public IAbilityService service() {
        return abilityService;
    }

    @Override
    public String basePermission() {
        return "ability:";
    }
}

