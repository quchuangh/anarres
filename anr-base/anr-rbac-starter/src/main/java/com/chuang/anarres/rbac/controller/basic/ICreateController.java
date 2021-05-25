package com.chuang.anarres.rbac.controller.basic;

import com.chuang.tauceti.rowquery.IRowQueryService;
import com.chuang.tauceti.support.Result;
import com.chuang.tauceti.support.exception.BusinessException;
import com.chuang.tauceti.tools.basic.reflect.ClassKit;
import com.chuang.tauceti.tools.basic.reflect.ConvertKit;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

public interface ICreateController<CO, RO, E, S extends IRowQueryService<E>> extends IBaseController<E, S>, IAutoPermissionController {

    @PostMapping(value = "/create")
    @ApiOperation("创建一条数据")
    @ResponseBody
    default Result<?> create(@RequestBody @ApiParam @Valid CO co) {
        checkPermission("create");
        E e = ClassKit.newInstance(entityClass()).orElseThrow(() -> new BusinessException("创建Entity失败"));
        RO r = ClassKit.newInstance(readEntityClass()).orElseThrow(() -> new BusinessException("创建RO失败"));

        return Result.whether(service().save(ConvertKit.toBean(co, () -> e)))
                .data(ConvertKit.toBean(e, ()-> r));
    }

    @SuppressWarnings("unchecked")
    default Class<CO> createEntityClass() {
        return (Class<CO>) ClassKit.getSuperClassGenericType(getClass(), ICreateController.class, 0);
    }
    @SuppressWarnings("unchecked")
    default Class<RO> readEntityClass() {
        return (Class<RO>) ClassKit.getSuperClassGenericType(getClass(), ICreateController.class, 1);
    }
}
