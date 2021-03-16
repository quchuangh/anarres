package com.chuang.anarres.office.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chuang.tauceti.support.Result;
import com.chuang.urras.rowquery.IRowQueryService;
import com.chuang.urras.rowquery.filters.RowQuery;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

public abstract class CrudController<T, S extends IRowQueryService<T>> {

    @Autowired
    protected S service;

    public abstract String permissionPrefix();

    @PostMapping("/query")
    @ResponseBody
    @ApiOperation("根据RowQuery对象进行查询")
    @ApiImplicitParam(name = "rowQuery", value = "查询记录", required = true, dataTypeClass = RowQuery.class)
    public IPage<T> query(@RequestBody RowQuery rowQuery) {
        this.checkPermission(":view");
        return service.pageByRowQuery(rowQuery);
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("根据id删除一条记录")
    @ResponseBody
    public Result<Boolean> delete(@PathVariable("id") String id) {
        this.checkPermission(":delete");
        // 添加操作日志
        return Result.whether(service.removeById(id));
    }

    @PostMapping("/delete")
    @ApiOperation("根据id删除一条记录")
    @ResponseBody
    public Result<Boolean> deleteBatch(String ids) {
        this.checkPermission(":delete");
        boolean deleted = service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.whether(deleted);
    }

    @PostMapping(value = "/create")
    @ApiOperation("创建一条数据")
    @ResponseBody
    public Result<Boolean> create(@RequestBody T entity) {
        this.checkPermission(":create");
        return Result.whether(service.save(entity));
    }

    @PutMapping("/update")
    @ApiOperation("更新一条记录")
    @ResponseBody
    public Result<Boolean> update(@RequestBody T vo) {
        this.checkPermission(":update");
        return Result.whether(service.updateById(vo));
    }


    protected void checkPermission(@NotNull String permission) {
        SecurityUtils.getSecurityManager().checkPermission(
                SecurityUtils.getSubject().getPrincipals(),
                permissionPrefix()  + permission
        );
    }

    protected boolean hasPermission(String permission) {
        return SecurityUtils.getSecurityManager().isPermitted(
                SecurityUtils.getSubject().getPrincipals(),
                permissionPrefix() +  permission
        );
    }
}
