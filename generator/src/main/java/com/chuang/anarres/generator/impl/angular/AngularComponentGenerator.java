package com.chuang.anarres.generator.impl.angular;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.chuang.tauceti.generator.Generator;
import com.chuang.tauceti.generator.config.GenConfig;
import com.chuang.tauceti.tools.basic.StringKit;

import java.util.HashMap;
import java.util.Map;

public abstract class AngularComponentGenerator implements Generator {
    @Override
    public Map<String, Object> initTableMap(GenConfig config, TableInfo tableInfo) {
        String name = tableNameWithoutPrefix(config, tableInfo);
        Map<String, Object> map = new HashMap<>();

        String camel = NamingStrategy.removePrefixAndCamel(name, config.getStrategy().getTablePrefix());


        map.put("angularCompSelectorName", "app-" + name.replaceAll("_", "-").toLowerCase());
        map.put("angularCompHtmlName", "." + name.replaceAll("_", "-").toLowerCase() + "/.component.html");
        map.put("angularCompClassName", StringKit.firstCharToUpperCase(camel) + "Component");
        map.put("angularApiPath", "/api" + name.replaceAll("_", "/").toLowerCase());
        return map;
    }
    @Override
    public String outputFile(GenConfig config, TableInfo info) {
        return "/angular/routes/" + info.getName() + ".component.ts";
    }
}
