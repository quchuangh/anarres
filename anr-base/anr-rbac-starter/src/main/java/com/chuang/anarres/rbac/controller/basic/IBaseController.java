package com.chuang.anarres.rbac.controller.basic;


import com.chuang.tauceti.rowquery.IRowQueryService;

public interface IBaseController<E, S extends IRowQueryService<E>> {
    S service();

    default Class<E> entityClass() {
        return service().getEntityClass();
    }
}
