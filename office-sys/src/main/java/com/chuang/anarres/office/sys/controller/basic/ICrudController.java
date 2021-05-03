package com.chuang.anarres.office.sys.controller.basic;

import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.chuang.urras.rowquery.IRowQueryService;

public interface ICrudController<CO, RO, UO, E, S extends IRowQueryService<E>> extends
        ICreateController<CO, E, S>,
        IRetrieveController<RO, E, S>,
        IUpdateController<UO, E, S>,
        IDeleteController<E, S> {

    @Override
    @SuppressWarnings("unchecked")
    default Class<RO> readEntityClass() {
        return (Class<RO>) ReflectionKit.getSuperClassGenericType(getClass(), 1);
    }

    @Override
    @SuppressWarnings("unchecked")
    default Class<UO> updateEntityClass() {
        return (Class<UO>) ReflectionKit.getSuperClassGenericType(getClass(), 2);
    }
}
