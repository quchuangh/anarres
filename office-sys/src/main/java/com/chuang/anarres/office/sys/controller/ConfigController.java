package com.chuang.anarres.office.sys.controller;

import com.chuang.anarres.office.sys.model.co.ConfigCO;
import com.chuang.anarres.office.sys.model.ro.ConfigRO;
import com.chuang.anarres.office.sys.model.uo.ConfigUO;
import com.chuang.anarres.office.sys.entity.Config;
import com.chuang.anarres.office.sys.service.IConfigService;
import com.chuang.anarres.office.sys.controller.basic.ICrudController;
import javax.annotation.Resource;


import com.chuang.tauceti.support.Result;
import com.chuang.tauceti.tools.basic.StringKit;
import com.chuang.tauceti.tools.basic.collection.CollectionKit;
import com.chuang.tauceti.tools.basic.reflect.ConvertKit;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 系统配置表  前端控制器
 * </p>
 *
 * @author chuang
 * @since 2021-05-11
 */
@RestController
@RequestMapping("/config")
public class ConfigController implements ICrudController<ConfigCO, ConfigRO, ConfigUO, Config, IConfigService> {

    @Resource private IConfigService service;

    @GetMapping("/all")
    @RequiresPermissions("config:read")
    public Result<List<ConfigRO>> all(String like) {
        if(StringKit.isBlank(like)) {
            return Result.success(service.list())
                    .map(configs -> ConvertKit.toBeans(configs, ConfigRO::new));
        } else {
            return Result.success(service.lambdaQuery().likeLeft(Config::getCode, like).list())
                    .map(configs -> ConvertKit.toBeans(configs, ConfigRO::new));
        }
    }

    @Override
    public IConfigService service() {
        return service;
    }

    @Override
    public String basePermission() {
        return "config:";
    }

}
