package com.chuang.anarres.office.sys.controller;


import com.chuang.anarres.office.sys.controller.basic.ICreateController;
import com.chuang.anarres.office.sys.controller.basic.IDeleteController;
import com.chuang.anarres.office.sys.entity.Ability;
import com.chuang.anarres.office.sys.model.co.AbilityCO;
import com.chuang.anarres.office.sys.model.ro.AbilityRO;
import com.chuang.anarres.office.sys.model.uo.TreeMoveUO;
import com.chuang.anarres.office.sys.service.IAbilityService;
import com.chuang.tauceti.support.Result;
import com.chuang.tauceti.tools.basic.reflect.ConvertKit;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
public class AbilityController implements
        ICreateController<AbilityCO, Ability, IAbilityService>,
        IDeleteController<Ability, IAbilityService> {

    @Resource private IAbilityService abilityService;

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

    @Override
    public IAbilityService service() {
        return abilityService;
    }
}

