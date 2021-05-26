package com.chuang.anarres.service.single;

import com.chuang.anarres.Utils;
import com.chuang.anarres.enums.CheckedStatus;
import com.chuang.anarres.enums.TaskStatus;
import com.chuang.anarres.model.SeoTemplate;
import com.chuang.tauceti.rowquery.IRowQueryService;
import com.chuang.tauceti.support.exception.BusinessException;

import javax.annotation.Nullable;
import java.util.*;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ath
 * @since 2020-09-19
 */
public interface ISeoTemplateService extends IRowQueryService<SeoTemplate> {
    default Optional<SeoTemplate> findByWebsite(String website) {
        return lambdaQuery().eq(SeoTemplate::getWebsite, website).oneOpt();
    }

    default List<SeoTemplate> findByWebsites(Collection<String> websites) {
        return lambdaQuery().in(SeoTemplate::getWebsite, websites).list();
    }

    default boolean audit(Integer id, Boolean like, String remark, String auditor) {
        SeoTemplate template = findById(id).orElseThrow(() -> new BusinessException(-1, "找不到这个模板记录"));
        if(template.getChecked().isDone()) {
            throw new BusinessException(-1, "已经被别人审核过");
        }
        if(template.getIndexState() == TaskStatus.ENDED &&
                template.getListState() == TaskStatus.ENDED &&
                template.getArticleState() == TaskStatus.ENDED) {
            template.setChecked(like ? CheckedStatus.PASS: CheckedStatus.REFUSE);
            template.setRemark(remark);
            template.setAuditor(auditor);

            return updateById(template);

        } else {
            throw new BusinessException(-1, "请仔细检查，系统发现有没有完成状态的页面");
        }

    }

    default List<SeoTemplate> random(int size) {
        List<SeoTemplate> list = lambdaQuery()
                .eq(SeoTemplate::getChecked, CheckedStatus.PASS)
                .orderByAsc(SeoTemplate::getUseTimes)
                .last(" limit " + size)
                .list();
        Utils.full(list, size);
        if(list.size() < size) {
            throw new BusinessException(-1, "无法获取足够的模板");
        }
        return list;
    }

    default List<SeoTemplate> random(@Nullable Integer groupId, int size) {
        List<SeoTemplate> list;
        if(null == groupId) {
            list = lambdaQuery().eq(SeoTemplate::getChecked, CheckedStatus.PASS).orderByAsc(SeoTemplate::getUseTimes).last(" limit " + size).list();
        } else {
            list = lambdaQuery().eq(SeoTemplate::getChecked, CheckedStatus.PASS).eq(SeoTemplate::getGroupId, groupId).orderByAsc(SeoTemplate::getUseTimes).last(" limit " + size).list();
        }
        Utils.full(list, size);
        return list;
    }

    default Map<Integer, List<SeoTemplate>> findByGroupCountMap(Map<Integer, Integer> groupCountMap) {
        Map<Integer, List<SeoTemplate>> map = new HashMap<>();
        groupCountMap.forEach((id, count) -> {
            List<SeoTemplate> list = lambdaQuery()
                    .eq(SeoTemplate::getGroupId, id)
                    .orderByAsc(SeoTemplate::getUseTimes)
                    .last(" limit " + count)
                    .list();
            Utils.full(list,count);
            map.put(id, list);
        });

        return map;
    }



}
