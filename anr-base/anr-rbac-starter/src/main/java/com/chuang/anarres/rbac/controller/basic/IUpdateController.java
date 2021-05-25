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

public interface IUpdateController<UO, E, S extends IRowQueryService<E>> extends IBaseController<E, S>, IAutoPermissionController {

    @PostMapping(value = "/update")
    @ApiOperation("修改资料")
    @ResponseBody
    default Result<Void> update(@Valid @RequestBody @ApiParam UO uo) {
        checkPermission("update");
        E e = ClassKit.newInstance(entityClass()).orElseThrow(() -> new BusinessException("创建Entity失败"));
        E newEntity = ConvertKit.toBean(uo, () -> e);
        return Result.whether(service().updateById(newEntity));
    }

    @SuppressWarnings("unchecked")
    default Class<UO> updateEntityClass() {
        return (Class<UO>) ClassKit.getSuperClassGenericType(getClass(), IUpdateController.class, 0);
    }
}
