package com.chuang.anarres.generator;

import com.chuang.tauceti.generator.GenType;

/**
 * order越小越先执行,
 * tau-ceti默认顺序为：
 * entity > mapper > service > serviceImpl > controller
 * 加入自定义实现后的顺序为：
 * entity > mapper > service > serviceImpl > SQL > ro > co > uo > controller > angularTs > angularHtml
 */
public interface CustomGenTypes {

    GenType SQL = GenType.parse("SQL", GenType.CONTROLLER.order() - 4);
    GenType Ro = GenType.parse("ro", GenType.CONTROLLER.order() - 3);
    GenType Co = GenType.parse("co", GenType.CONTROLLER.order() - 2);
    GenType Uo = GenType.parse("uo", GenType.CONTROLLER.order() - 1);
    GenType AngularTs = GenType.parse("angular-component-ts", GenType.CONTROLLER.order() + 1);
    GenType AngularHtml = GenType.parse("angular-component-html", GenType.CONTROLLER.order() + 2);
    GenType AngularCreateTs = GenType.parse("angular-create-component-ts", GenType.CONTROLLER.order() + 3);
    GenType AngularCreateHtml = GenType.parse("angular-create-component-html", GenType.CONTROLLER.order() + 4);
    GenType AngularEditTs = GenType.parse("angular-edit-component-ts", GenType.CONTROLLER.order() + 5);
    GenType AngularEditHtml = GenType.parse("angular-edit-component-html", GenType.CONTROLLER.order() + 6);
    GenType AngularViewTs = GenType.parse("angular-view-component-ts", GenType.CONTROLLER.order() + 7);
    GenType AngularViewHtml = GenType.parse("angular-view-component-html", GenType.CONTROLLER.order() + 8);
}
