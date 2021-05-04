package com.chuang.anarres.generator.impl.model;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.chuang.anarres.generator.CustomGenTypes;
import com.chuang.tauceti.generator.GenType;
import com.chuang.tauceti.generator.config.GenConfig;

import java.util.HashMap;
import java.util.Map;

public class UoGen extends EntityGen {
    @Override
    public String template() {
        return "/templates/java/uo.java.vm";
    }

    @Override
    public GenType type() {
        return CustomGenTypes.Uo;
    }
    @Override
    public Map<String, Object> initTableMap(GenConfig config, TableInfo tableInfo) {
        Map<String, Object> tableContext = new HashMap<>();

        tableContext.put("packageUo", pkg(config, tableInfo) + "." + tableInfo.getEntityName() + "UO");
        tableContext.put("classNameUo", tableInfo.getEntityName() + "UO");
        return tableContext;
    }

    @Override
    public String pkg(GenConfig genConfig, TableInfo info) {
        return "com.chuang.anarres.office.sys.model.uo";
    }
}
