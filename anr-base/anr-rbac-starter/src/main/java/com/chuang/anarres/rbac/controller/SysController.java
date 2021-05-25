package com.chuang.anarres.rbac.controller;

import com.chuang.anarres.rbac.model.ro.AppRO;
import com.chuang.tauceti.support.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/sys")
@RestController
public class SysController {

    @Value("${app.name}") private String appName;
    @Value("${app.description}") private String appDescription;

    @GetMapping("/app-info")
    public Result<AppRO> appInfo() {
        AppRO app = new AppRO();
        app.setDescription(appDescription);
        app.setName(appName);
        return Result.success(app);
    }
}
