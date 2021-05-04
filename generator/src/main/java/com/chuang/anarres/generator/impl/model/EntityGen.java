package com.chuang.anarres.generator.impl.model;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.chuang.tauceti.generator.config.GenConfig;

public class EntityGen extends com.chuang.tauceti.generator.impl.EntityGen {
    public String outputFile(GenConfig config, TableInfo info) {
        return "/office-sys/src/main/java" + super.outputFile(config, info);
    }
}
