package com.chuang.anarres.office.sys.controller.basic;

import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.chuang.tauceti.support.Result;
import com.chuang.tauceti.support.exception.BusinessException;
import com.chuang.tauceti.tools.basic.reflect.ClassKit;
import com.chuang.tauceti.tools.basic.reflect.ConvertKit;
import com.chuang.urras.rowquery.IRowQueryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public interface ICreateController<CO, E, S extends IRowQueryService<E>> extends RowQueryController<E, S> {

    @PostMapping(value = "/create")
    @ApiOperation("创建一条数据")
    @ResponseBody
    default Result<Void> create(@RequestBody CO co) {
        E e = ClassKit.newInstance(entityClass()).orElseThrow(() -> new BusinessException("创建Entity失败"));
        return Result.whether(service().save(ConvertKit.toBean(co, () -> e)));
    }

    @SuppressWarnings("unchecked")
    default Class<CO> createEntityClass() {
        return (Class<CO>) ReflectionKit.getSuperClassGenericType(getClass(), 1);
    }
}
