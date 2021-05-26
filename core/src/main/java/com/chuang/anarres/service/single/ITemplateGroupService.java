package com.chuang.anarres.service.single;

import com.chuang.anarres.model.TemplateGroup;
import com.chuang.tauceti.rowquery.IRowQueryService;
import com.chuang.tauceti.support.exception.BusinessException;
import com.chuang.tauceti.tools.basic.collection.CollectionKit;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ath
 * @since 2020-09-23
 */
public interface ITemplateGroupService extends IRowQueryService<TemplateGroup> {

    default List<TemplateGroup> checkAndFind(Collection<String> groupNames) {

        List<TemplateGroup> list = lambdaQuery().in(TemplateGroup::getGroup, groupNames).list();
        if(list.size() < groupNames.size()) {
            Set<String> names = list.stream().map(TemplateGroup::getGroup).collect(Collectors.toSet());
            names = CollectionKit.subtract(groupNames, names, HashSet::new);
            throw new BusinessException(-1, CollectionKit.toString(names) + "没有找到相关组");
        }

        return list;

    }
}
