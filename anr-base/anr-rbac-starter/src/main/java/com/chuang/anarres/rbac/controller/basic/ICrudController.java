package com.chuang.anarres.rbac.controller.basic;

import com.chuang.tauceti.rowquery.IRowQueryService;
import com.chuang.tauceti.tools.basic.reflect.ClassKit;

public interface ICrudController<CO, RO, UO, E, S extends IRowQueryService<E>> extends
        ICreateController<CO, RO, E, S>,
        IRetrieveController<RO, E, S>,
        IUpdateController<UO, E, S>,
        IDeleteController<E, S> {

    @Override
    @SuppressWarnings("unchecked")
    default Class<RO> readEntityClass() {
        return (Class<RO>) ClassKit.getSuperClassGenericType(getClass(), ICrudController.class, 1);
    }

    @SuppressWarnings("unchecked")
    default Class<CO> createEntityClass() {
        return (Class<CO>) ClassKit.getSuperClassGenericType(getClass(), ICrudController.class, 0);
    }

    @Override
    @SuppressWarnings("unchecked")
    default Class<UO> updateEntityClass() {
        return (Class<UO>) ClassKit.getSuperClassGenericType(getClass(), ICrudController.class,2);
    }
}
