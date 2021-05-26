package com.chuang.anarres.generator.impl.angular;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.chuang.anarres.generator.CustomGenTypes;
import com.chuang.tauceti.generator.GenType;
import com.chuang.tauceti.generator.config.GenConfig;

public class ComponentTs extends AngularComponentGenerator {
    @Override
    public String template() {
        return "/templates/angular/component.ts.vm";
    }

    @Override
    public GenType type() {
        return CustomGenTypes.AngularTs;
    }

    @Override
    public String outputFile(GenConfig config, TableInfo info) {
        String fileName = tableNameWithoutPrefix(config, info).replaceAll("_", "-").toLowerCase();
        return "/a-makes/angular/routes/" + fileName + "/" + fileName + ".component.ts";
    }

}
