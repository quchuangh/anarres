package com.chuang.anarres.generator.impl;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.chuang.tauceti.generator.config.GenConfig;
import com.chuang.tauceti.generator.impl.MapperGen;

public class Mapper extends MapperGen {

    public String outputFile(GenConfig config, TableInfo info) {
        return "/office-base/src/main/java" + super.outputFile(config, info);
    }
}
