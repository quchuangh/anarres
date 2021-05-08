package com.chuang.anarres.generator.impl;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.chuang.anarres.generator.CustomGenTypes;
import com.chuang.tauceti.generator.GenType;
import com.chuang.tauceti.generator.Generator;
import com.chuang.tauceti.generator.config.GenConfig;

import java.util.HashMap;
import java.util.Map;

public class SQLGen implements Generator {
    @Override
    public String template() {
        return "/templates/sql/sql.vm";
    }

    @Override
    public GenType type() {
        return CustomGenTypes.SQL;
    }

    @Override
    public Map<String, Object> initTableMap(GenConfig config, TableInfo tableInfo) {
        Map<String, Object> tableContext = new HashMap<>();
        tableContext.put("parentMenu", "system");
        return tableContext;
    }

    @Override
    public String outputFile(GenConfig config, TableInfo info) {
        return "/a-makes/SQL/" + info.getName() + ".sql";
    }
}
