package com.chuang.anarres.office.sys.controller.basic;

import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.chuang.tauceti.support.Result;
import com.chuang.tauceti.support.exception.BusinessException;
import com.chuang.tauceti.tools.basic.reflect.ConvertKit;
import com.chuang.urras.rowquery.IRowQueryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

public interface IUpdateController<UO, E, S extends IRowQueryService<E>> extends RowQueryController<E, S> {

    @PostMapping(value = "/update/{id}")
    @ApiOperation("修改资料")
    @ResponseBody
    default Result<Void> update(@PathVariable("id") String id, @Valid @RequestBody UO uo) {
        E entity = service().findById(id).orElseThrow(() -> new BusinessException("无法找到id为{}的记录", id));
        E newEntity = ConvertKit.toBean(uo, () -> entity);
        return Result.whether(service().updateById(newEntity));
    }

    @SuppressWarnings("unchecked")
    default Class<UO> updateEntityClass() {
        return (Class<UO>) ReflectionKit.getSuperClassGenericType(getClass(), 1);
    }
}
