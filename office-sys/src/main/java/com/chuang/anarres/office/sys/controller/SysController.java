package com.chuang.anarres.office.sys.controller;

import com.chuang.anarres.office.sys.model.vo.AppVO;
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
    public Result<AppVO> appInfo() {
        AppVO app = new AppVO();
        app.setDescription(appDescription);
        app.setName(appName);
        return Result.success(app);
    }
}
