package com.chuang.anarres.generator.impl;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.chuang.tauceti.generator.config.GenConfig;
import com.chuang.tauceti.generator.impl.ServiceGen;

public class Service extends ServiceGen {
    public String outputFile(GenConfig config, TableInfo info) {
        return "/core/src/main/java" + super.outputFile(config, info);
    }
}
