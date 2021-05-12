package com.chuang.anarres.office.sys.controller;

import com.chuang.anarres.office.sys.model.co.DictItemCO;
import com.chuang.anarres.office.sys.model.ro.AbilityRO;
import com.chuang.anarres.office.sys.model.ro.DictItemRO;
import com.chuang.anarres.office.sys.model.uo.DictItemUO;
import com.chuang.anarres.office.sys.entity.DictItem;
import com.chuang.anarres.office.sys.model.uo.TreeMoveUO;
import com.chuang.anarres.office.sys.service.IDictItemService;
import com.chuang.anarres.office.sys.controller.basic.ICrudController;
import javax.annotation.Resource;


import com.chuang.tauceti.support.Result;
import com.chuang.tauceti.tools.basic.reflect.ConvertKit;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 字典项; 前端控制器
 * </p>
 *
 * @author chuang
 * @since 2021-05-11
 */
@RestController
@RequestMapping("/dict/item")
public class DictItemController implements ICrudController<DictItemCO, DictItemRO, DictItemUO, DictItem, IDictItemService> {

    @Resource private IDictItemService service;

    @PostMapping("/move")
    @ApiOperation("移动菜单")
    public Result<Boolean> move(@RequestBody @ApiParam TreeMoveUO move) {
        return Result.success(this.service.move(move.getFrom(), move.getTo(), move.getPos()));
    }

    @GetMapping("/all")
    public Result<List<DictItemRO>> all() {
        return Result.success(
                ConvertKit.toBeans(service.list(), DictItemRO::new)
                        .stream()
                        .sorted(Comparator.comparing(DictItemRO::getSortRank))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public IDictItemService service() {
        return service;
    }

    @Override
    public String basePermission() {
        return "dict:";
    }

}

