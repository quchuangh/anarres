package com.chuang.anarres.rbac.controller.basic;

import com.chuang.tauceti.rowquery.IRowQueryService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DefaultCrudController<CO, RO, UO, E, S extends IRowQueryService<E>> implements ICrudController<CO, RO, UO, E, S> {
    @Autowired
    protected S service;

    public S service() {
        return service;
    }
}
