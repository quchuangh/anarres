package com.chuang.anarres.office.sys.controller.basic;

import com.chuang.urras.rowquery.IRowQueryService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DefaultCrudController<CO, RO, UO, E, S extends IRowQueryService<E>> implements ICrudController<CO, RO, UO, E, S> {
    @Autowired
    protected S service;

    public S service() {
        return service;
    }
}
