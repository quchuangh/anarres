package com.chuang.anarres.generator.impl.angular;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.chuang.tauceti.generator.GenType;
import com.chuang.tauceti.generator.config.GenConfig;

public class ComponentHtml extends AngularComponentGenerator {
    @Override
    public String template() {
        return "/templates/angular/component.html.vm";
    }

    @Override
    public GenType type() {
        return new GenType() {
            @Override
            public String name() {
                return "angular-component-html";
            }

            @Override
            public int order() {
                return 0;
            }
        };
    }

    @Override
    public String outputFile(GenConfig config, TableInfo info) {
        return "/angular/routes/" + info.getName() + ".component.html";
    }
}
