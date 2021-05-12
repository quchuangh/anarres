package com.chuang.anarres.office.sys.dict;

import com.chuang.anarres.office.sys.entity.DictItem;
import com.chuang.anarres.office.sys.service.IDictItemService;
import com.chuang.anarres.office.sys.service.IDictTypeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DictManager {
    @Resource private IDictTypeService dictTypeService;
    @Resource private IDictItemService dictItemService;

    public List<Byte> values(String dictType) {
        return dictItemService.findByType(dictType).stream()
                .map(DictItem::getVal)
                .collect(Collectors.toList());
    }

    public boolean contains(String dictType, Byte value) {
        return values(dictType).contains(value);
    }


}
