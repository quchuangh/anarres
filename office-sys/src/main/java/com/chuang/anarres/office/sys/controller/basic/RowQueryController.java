package com.chuang.anarres.office.sys.controller.basic;

import com.chuang.urras.rowquery.IRowQueryService;

public interface RowQueryController<E, S extends IRowQueryService<E>> {
    S service();

    default Class<E> entityClass() {
        return service().currentModelClass();
    }
}
