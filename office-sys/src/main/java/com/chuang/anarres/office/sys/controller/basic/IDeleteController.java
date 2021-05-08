package com.chuang.anarres.office.sys.controller.basic;

import com.chuang.tauceti.support.Result;
import com.chuang.urras.rowquery.IRowQueryService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
