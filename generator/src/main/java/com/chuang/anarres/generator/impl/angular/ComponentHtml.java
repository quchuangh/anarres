package com.chuang.anarres.generator.impl.angular;

import com.chuang.tauceti.generator.GenType;

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
}
