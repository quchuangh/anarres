package com.chuang.anarres.generator.impl;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.chuang.tauceti.generator.config.GenConfig;
import com.chuang.tauceti.generator.impl.ServiceImplGen;

public class ServiceImpl extends ServiceImplGen {
    public String outputFile(GenConfig config, TableInfo info) {
        return "/office-sys/src/main/java" + super.outputFile(config, info);
    }
}
