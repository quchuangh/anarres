package com.chuang.anarres.generator.impl.model;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.chuang.anarres.generator.CustomGenTypes;
import com.chuang.tauceti.generator.GenType;
import com.chuang.tauceti.generator.JavaGenerator;
import com.chuang.tauceti.generator.config.GenConfig;

import java.util.HashMap;
import java.util.Map;

public class CoGen implements JavaGenerator {
    @Override
    public String template() {
        return "/templates/java/co.java.vm";
    }

    @Override
    public GenType type() {
        return CustomGenTypes.Co;
    }

    @Override
    public Map<String, Object> initTableMap(GenConfig config, TableInfo tableInfo) {
        Map<String, Object> tableContext = new HashMap<>();

        tableContext.put("packageCo", pkg(config, tableInfo));
        tableContext.put("classNameCo", tableInfo.getEntityName() + "CO");
        return tableContext;
    }

    public String outputFile(GenConfig config, TableInfo info) {
        return "/office/src/main/java/" + this.pkg(config, info).replaceAll("\\.", "/") + "/" + info.getEntityName() + "CO.java";
    }

    @Override
    public String pkg(GenConfig genConfig, TableInfo info) {
        return "com.chuang.anarres.office.sys.model.co";
    }
}
