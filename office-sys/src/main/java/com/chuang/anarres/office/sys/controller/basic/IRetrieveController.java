package com.chuang.anarres.office.sys.controller.basic;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.chuang.tauceti.support.exception.BusinessException;
import com.chuang.tauceti.tools.basic.reflect.ClassKit;
import com.chuang.tauceti.tools.basic.reflect.ConvertKit;
import com.chuang.urras.rowquery.IRowQueryService;
import com.chuang.urras.rowquery.RowQuery;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public interface IRetrieveController<RO, E, S extends IRowQueryService<E>> extends IBaseController<E, S>, IAutoPermissionController {



    @PostMapping("/query")
    @ResponseBody
    @ApiOperation("根据RowQuery对象进行查询")
    @ApiImplicitParam(name = "rowQuery", value = "查询记录", required = true, dataTypeClass = RowQuery.class)
    default IPage<RO> query(@RequestBody RowQuery rowQuery) {
        checkPermission("read");
        return service().pageByRowQuery(rowQuery).convert(e -> {
            RO ro = ClassKit.newInstance(readEntityClass()).orElseThrow(() -> new BusinessException("创建RO对象失败"));
            return ConvertKit.toBean(e, () -> ro);
        });
    }

    @SuppressWarnings("unchecked")
    default Class<RO> readEntityClass() {
        return (Class<RO>) ClassKit.getSuperClassGenericType(getClass(), IRetrieveController.class, 0);
    }
}
