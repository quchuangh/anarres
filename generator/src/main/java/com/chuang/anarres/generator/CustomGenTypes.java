package com.chuang.anarres.generator;

import com.chuang.tauceti.generator.GenType;

public interface CustomGenTypes {
    GenType AngularTs = GenType.parse("angular-component-ts");
    GenType AngularHtml = GenType.parse("angular-component-html");
    GenType Ro = GenType.parse("ro");
    GenType Uo = GenType.parse("uo");
    GenType Co = GenType.parse("co");
}
