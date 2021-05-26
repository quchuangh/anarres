package com.chuang.anarres.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuang.anarres.model.Article;
import com.chuang.tauceti.support.BiValue;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ath
 * @since 2020-09-23
 */
public interface ArticleMapper extends BaseMapper<Article> {

    @Select("SELECT  t.type as `key`, count(*) as `value` " +
            "FROM t_article t " +
            "WHERE t.used = false " +
            "GROUP BY t.type")
    List<BiValue<Integer, Integer>> statisticsNotUsed();
}
