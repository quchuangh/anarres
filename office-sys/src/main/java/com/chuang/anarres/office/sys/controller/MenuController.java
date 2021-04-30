package com.chuang.anarres.office.sys.controller;


import com.alibaba.fastjson.JSONObject;
import com.chuang.anarres.office.sys.entity.Menu;
import com.chuang.anarres.office.sys.model.qo.MenuQO;
import com.chuang.anarres.office.sys.model.qo.TreeMoveQO;
import com.chuang.anarres.office.sys.model.bo.AclBO;
import com.chuang.anarres.office.sys.model.vo.MenuVO;
import com.chuang.anarres.office.sys.service.IMenuService;
import com.chuang.tauceti.support.Result;
import com.chuang.tauceti.tools.basic.StringKit;
import com.chuang.tauceti.tools.basic.reflect.ConvertKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
public class MenuController {

    @Resource private IMenuService menuService;

    @PostMapping("/save")
    @ApiOperation("创建一条数据")
    public Result<Boolean> save(@RequestBody @ApiParam MenuQO qo) {
        Menu menu = ConvertKit.toBean(qo, Menu::new);
        menu.setExternalLink("");
        return Result.whether(menuService.saveAndCheckParents(menu));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除一条数据")
    public Result<Boolean> delete(@NotNull @PathVariable("id") Integer id) {
        return Result.success(menuService.removeById(id));
    }

    @PostMapping("/move")
    @ApiOperation("移动菜单")
    public Result<Boolean> move(@RequestBody @ApiParam TreeMoveQO move) {
        return Result.success(this.menuService.move(move.getFrom(), move.getTo(), move.getPos()));
    }

    @GetMapping("/all")
    public Result<List<MenuVO>> menu() {
        return Result.success(
                menuService.list().stream().map(menu -> {
                    MenuVO vo = ConvertKit.toBean(menu, MenuVO::new);
                    if(StringKit.isNotBlank(menu.getAcl())) {
                        vo.setAcl(JSONObject.parseObject(menu.getAcl(), AclBO.class));
                    }
                    return vo;
                })
                .sorted(Comparator.comparing(MenuVO::getSortRank))
                .collect(Collectors.toList())
        );
    }


}

