package com.chuang.anarres.generator.impl.angular;

import com.chuang.tauceti.generator.GenType;

public class ComponentTs extends AngularComponentGenerator {
    @Override
    public String template() {
        return "/templates/angular/component.ts.vm";
    }

    @Override
    public GenType type() {
        return new GenType() {
            @Override
            public String name() {
                return "angular-component-ts";
            }

            @Override
            public int order() {
                return 0;
            }
        };
    }

}
