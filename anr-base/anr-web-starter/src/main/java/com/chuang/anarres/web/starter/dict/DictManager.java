package com.chuang.anarres.web.starter.dict;

import com.chuang.anarres.crud.entity.DictItem;
import com.chuang.anarres.crud.service.IDictItemService;
import com.chuang.anarres.crud.service.IDictTypeService;
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
