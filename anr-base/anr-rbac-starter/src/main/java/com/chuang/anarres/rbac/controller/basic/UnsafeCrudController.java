package com.chuang.anarres.rbac.controller.basic;

import com.chuang.tauceti.rowquery.IRowQueryService;

public abstract class UnsafeCrudController<E, S extends IRowQueryService<E>> extends DefaultCrudController<E, E, E, E, S> {
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
