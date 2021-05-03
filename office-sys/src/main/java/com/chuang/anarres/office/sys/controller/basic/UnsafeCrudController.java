package com.chuang.anarres.office.sys.controller.basic;

import com.chuang.urras.rowquery.IRowQueryService;

public class UnsafeCrudController<E, S extends IRowQueryService<E>> extends DefaultCrudController<E, E, E, E, S> {
    @Override
    public Class<E> readEntityClass() {
        return entityClass();
    }

    @Override
    public Class<E> updateEntityClass() {
        return entityClass();
    }

    @Override
    public Class<E> createEntityClass() {
        return entityClass();
    }
}
