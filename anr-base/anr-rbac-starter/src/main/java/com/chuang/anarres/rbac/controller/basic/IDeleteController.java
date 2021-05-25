package com.chuang.anarres.rbac.controller.basic;

import com.chuang.tauceti.rowquery.IRowQueryService;
import com.chuang.tauceti.support.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

public interface IDeleteController<E, S extends IRowQueryService<E>> extends IBaseController<E, S>, IAutoPermissionController {

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation("删除以条记录")
    @ResponseBody
    default Result<Void> delete(@PathVariable("id") String id) {
        checkPermission("delete");
        return Result.whether(service().removeById(id));
    }

}
