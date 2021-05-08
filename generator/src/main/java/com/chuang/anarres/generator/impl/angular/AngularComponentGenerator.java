package com.chuang.anarres.generator.impl.angular;

import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.chuang.tauceti.generator.Generator;
import com.chuang.tauceti.generator.config.GenConfig;
import com.chuang.tauceti.tools.basic.StringKit;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AngularComponentGenerator implements Generator {
    @Override
    public Map<String, Object> initTableMap(GenConfig config, TableInfo tableInfo) {
        String name = tableNameWithoutPrefix(config, tableInfo);
        Map<String, Object> map = new HashMap<>();

        String camel = NamingStrategy.removePrefixAndCamel(name, config.getStrategy().getTablePrefix());

        String requires = tableInfo.getFields().stream()
                .filter(field ->
                    StringKit.isBlank(field.getFill()) && !field.isKeyFlag()
                )
                .map(TableField::getName)
                .collect(Collectors.joining("', '"));

        map.put("angularCompSelectorName", name.replaceAll("_", "-").toLowerCase());
        map.put("angularCompHtmlName", "./" + name.replaceAll("_", "-").toLowerCase() + ".component.html");
        map.put("angularCompClassName", StringKit.firstCharToUpperCase(camel));
        map.put("angularApiPath", "/api/" + name.replaceAll("_", "/").toLowerCase());
        map.put("angularCompSfRequires", "'" + requires + "'");
        return map;
    }

}
