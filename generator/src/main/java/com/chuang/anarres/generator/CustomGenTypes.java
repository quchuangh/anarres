package com.chuang.anarres.generator;

import com.chuang.tauceti.generator.GenType;

/**
 * order越小越先执行,
 * tau-ceti默认顺序为：
 * entity > mapper > service > serviceImpl > controller
 * 加入自定义实现后的顺序为：
 * entity > mapper > service > serviceImpl > ro > co > uo > controller > angularTs > angularHtml
 */
public interface CustomGenTypes {

    GenType Ro = GenType.parse("ro", GenType.CONTROLLER.order() - 3);
    GenType Co = GenType.parse("co", GenType.CONTROLLER.order() - 2);
    GenType Uo = GenType.parse("uo", GenType.CONTROLLER.order() - 1);
    GenType AngularTs = GenType.parse("angular-component-ts", GenType.CONTROLLER.order() + 1);
    GenType AngularHtml = GenType.parse("angular-component-html", GenType.CONTROLLER.order() + 2);
}
