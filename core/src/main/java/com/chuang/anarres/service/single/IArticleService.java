package com.chuang.anarres.service.single;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chuang.anarres.enums.DomainType;
import com.chuang.anarres.exception.NohaveArticleException;
import com.chuang.anarres.model.Article;
import com.chuang.anarres.model.SeoDomain;
import com.chuang.tauceti.rowquery.IRowQueryService;
import com.chuang.tauceti.support.BiValue;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ath
 * @since 2020-09-23
 */
public interface IArticleService extends IRowQueryService<Article> {

    default boolean saveBatchSafe(Collection<Article> articles) {
        Set<String> md5s = articles.stream().map(Article::getMd5).collect(Collectors.toSet());
        Set<String> existedList = lambdaQuery().select(Article::getMd5).in(Article::getMd5, md5s).list() // 查找数据库中已经存在的MD5
                .stream().map(Article::getMd5).collect(Collectors.toSet()); //将已经存在的MD5放入集合

        if(!existedList.isEmpty()) {
            articles = articles.stream().filter(article -> !existedList.contains(article.getMd5())).collect(Collectors.toSet());
        }

        return this.saveBatch(articles);
    }

    List<BiValue<DomainType, Integer>> statisticsNotUsed();

    default List<Article> findNotUsed(DomainType type, int size) {
        List<Article> list =
                lambdaQuery().eq(Article::getType, type).eq(Article::getUsed, false).last(" limit " + size + " ").list();
        if(list.size() < size) {
            throw new NohaveArticleException(-1, "文章数量不够");
        }

        return list;
    }

    /**
     *
     * @param domain 域名
     * @param menu 是否为菜单文章
     * @param lastSize 条目
     */
    default List<Article> findIdAndTitleByDomain(String domain, Boolean menu, int lastSize) {
        if(lastSize  < 1) {
            return Collections.emptyList();
        }
        return lambdaQuery()
                .select(Article::getId, Article::getTitle)
                .eq(Article::getOwner, domain)
                .eq(Article::getMenu, menu)
                .orderByDesc(Article::getCreatedTime)
                .last(" limit " + lastSize + " ").list();
    }


    default List<Article> findInterlink(String domain, int beginId, int size) {

        List<Article> list = lambdaQuery()
                .eq(Article::getOwner, domain)
                .gt(Article::getId, beginId)
                .eq(Article::getMenu, false)
                .last(" limit " + size + " ")
                .list();

        if(list.size() < size) { // 如果不够，则从头开始
            int needMore = size - list.size();
            List<Article> more = lambdaQuery()
                    .eq(Article::getOwner, domain)
                    .eq(Article::getMenu, false)
                    .orderByAsc(Article::getId)
                    .last(" limit " + needMore + " ")
                    .list();
            list.addAll(more);
        }


        return list;
    }

    default List<Article> findByOwner(String domain) {
        return lambdaQuery().eq(Article::getOwner, domain).list();
    }

    default Collection<String> links(SeoDomain domain) {
        JSONArray menus = JSONArray.parseArray(domain.getMenus());
        Set<String> urls = new HashSet<>();
        urls.add("");//首页
        for (int i = 0; i < menus.size(); i++) { //菜单
            JSONObject json = menus.getJSONObject(i);
            urls.add(json.getString("pinyin"));
        }
        lambdaQuery()
                .select(Article::getId)
                .eq(Article::getOwner, domain.getDomain())
                .list()
                .forEach(article -> urls.add(domain.getDir() + "/" + article.getId() + domain.getSuffix()));

        return urls;
    }
}
