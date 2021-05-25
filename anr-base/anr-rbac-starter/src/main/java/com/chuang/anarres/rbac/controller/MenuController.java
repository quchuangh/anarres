package com.chuang.anarres.rbac.controller;


import com.chuang.anarres.rbac.controller.basic.ICrudController;
import com.chuang.anarres.crud.entity.Menu;
import com.chuang.anarres.rbac.model.co.MenuCO;
import com.chuang.anarres.rbac.model.uo.MenuUO;
import com.chuang.anarres.rbac.model.uo.TreeMoveUO;
import com.chuang.anarres.rbac.model.ro.MenuRO;
import com.chuang.anarres.crud.service.IMenuService;
import com.chuang.tauceti.support.Result;
import com.chuang.tauceti.tools.basic.reflect.ConvertKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表; 前端控制器
 * </p>
 *
 * @author chuang
 * @since 2020-12-20
 */
@Valid
@Api(tags = "菜单模块")
@RestController
@RequestMapping("/sys/menu")
public class MenuController implements ICrudController<MenuCO, MenuRO, MenuUO, Menu, IMenuService> {

    @Resource private IMenuService menuService;

    @PostMapping("/move")
    @ApiOperation("移动菜单")
    public Result<Boolean> move(@RequestBody @ApiParam TreeMoveUO move) {
        return Result.success(this.menuService.move(move.getFrom(), move.getTo(), move.getPos()));
    }

    @GetMapping("/all")
    public Result<List<MenuRO>> menu() {
        return Result.success(
                menuService.list().stream().map(menu -> ConvertKit.toBean(menu, MenuRO::new))
                .sorted(Comparator.comparing(MenuRO::getSortRank))
                .collect(Collectors.toList())
        );
    }


    @Override
    public String basePermission() {
        return "menu:";
    }

    @Override
    public IMenuService service() {
        return menuService;
    }
}

