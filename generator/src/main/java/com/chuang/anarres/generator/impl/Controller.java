package com.chuang.anarres.generator.impl;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.chuang.tauceti.generator.config.GenConfig;
import com.chuang.tauceti.generator.impl.ControllerGen;
import com.chuang.tauceti.tools.basic.StringKit;

import java.util.HashMap;
import java.util.Map;

public class Controller extends ControllerGen {

    public String template() {
        return "/templates/java/controller.java.vm";
    }

    @Override
    public Map<String, Object> initTableMap(GenConfig config, TableInfo tableInfo) {
        Map<String, Object> context = new HashMap<>();
        String role = "";
        if(StringKit.isNotBlank(config.getPackageCfg().getModuleName())) {
            role += config.getPackageCfg().getModuleName() + ":";
        }


        context.put("rolePrefix", role + tableInfo.getEntityPath() + ":");
        return context;
    }

    public String outputFile(GenConfig config, TableInfo info) {
        return "/office-sys/src/main/java" + super.outputFile(config, info);
    }
}
