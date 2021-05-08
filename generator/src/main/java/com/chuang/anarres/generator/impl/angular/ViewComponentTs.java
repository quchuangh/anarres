package com.chuang.anarres.generator.impl.angular;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.chuang.anarres.generator.CustomGenTypes;
import com.chuang.tauceti.generator.GenType;
import com.chuang.tauceti.generator.config.GenConfig;

public class ViewComponentTs extends AngularComponentGenerator {
    @Override
    public String template() {
        return "/templates/angular/view.component.ts.vm";
    }

    @Override
    public GenType type() {
        return CustomGenTypes.AngularViewTs;
    }

    @Override
    public String outputFile(GenConfig config, TableInfo info) {
        return "/a-makes/angular/routes/" + info.getName() + "/modal/view.component.ts";
    }

}
