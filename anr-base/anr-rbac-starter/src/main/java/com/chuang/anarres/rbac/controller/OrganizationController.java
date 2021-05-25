package com.chuang.anarres.rbac.controller;

import com.chuang.anarres.rbac.model.co.OrganizationCO;
import com.chuang.anarres.rbac.model.ro.OrganizationRO;
import com.chuang.anarres.rbac.model.uo.OrganizationUO;
import com.chuang.anarres.crud.entity.Organization;
import com.chuang.anarres.rbac.model.uo.TreeMoveUO;
import com.chuang.anarres.crud.service.IOrganizationService;
import com.chuang.anarres.rbac.controller.basic.ICrudController;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;


import com.chuang.tauceti.support.Result;
import com.chuang.tauceti.tools.basic.reflect.ConvertKit;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 组织表; 前端控制器
 * </p>
 *
 * @author chuang
 * @since 2021-05-24
 */
@RestController
@RequestMapping("/sys/organization")
public class OrganizationController implements ICrudController<OrganizationCO, OrganizationRO, OrganizationUO, Organization, IOrganizationService> {

    @Resource private IOrganizationService service;

    @PostMapping("/move")
    @ApiOperation("移动菜单")
    public Result<Boolean> move(@RequestBody @ApiParam TreeMoveUO move) {
        return Result.success(this.service.move(move.getFrom(), move.getTo(), move.getPos()));
    }

    @GetMapping("/all")
    public Result<List<OrganizationRO>> all() {
        return Result.success(
                ConvertKit.toBeans(service.list(), OrganizationRO::new)
                        .stream()
                        .sorted(Comparator.comparing(OrganizationRO::getSortRank))
                        .collect(Collectors.toList())
        );
    }

    @RequiresPermissions("user:join-group")
    @GetMapping(value = "/joined/{username}")
    @ApiOperation("已经加入组织")
    public Result<List<OrganizationRO>> joinedOrg(@PathVariable @Valid @NotEmpty String username) {
        List<Organization> list = service.findJoined(username);
        return Result.success(ConvertKit.toBeans(list, OrganizationRO::new));
    }

    @Override
    public IOrganizationService service() {
        return service;
    }

    @Override
    public String basePermission() {
        return "organization:";
    }

}

