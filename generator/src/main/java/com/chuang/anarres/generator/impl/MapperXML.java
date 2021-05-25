package com.chuang.anarres.generator.impl;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.chuang.tauceti.generator.config.GenConfig;
import com.chuang.tauceti.generator.impl.MapperXmlGen;

public class MapperXML extends MapperXmlGen {

    public String outputFile(GenConfig config, TableInfo info) {
        return "/core/src/main/resources" + super.outputFile(config, info);
    }
}
