package com.chuang.anarres.office.sys.controller.basic;

import com.chuang.tauceti.support.Result;
import com.chuang.urras.rowquery.IRowQueryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public interface IDeleteController<E, S extends IRowQueryService<E>> extends RowQueryController<E, S> {

    @PostMapping(value = "/delete/{id}")
    @ApiOperation("删除以条记录")
    @ResponseBody
    default Result<Void> create(@PathVariable("id") String id) {
        return Result.whether(service().removeById(id));
    }

}
