package com.chuang.anarres.service.single.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.anarres.enums.DomainType;
import com.chuang.anarres.mapper.ArticleMapper;
import com.chuang.anarres.model.Article;
import com.chuang.anarres.service.single.IArticleService;
import com.chuang.tauceti.support.BiValue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ath
 * @since 2020-09-23
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Override
    public List<BiValue<DomainType, Integer>> statisticsNotUsed() {
        List<BiValue<Integer, Integer>> list = baseMapper.statisticsNotUsed();
        return list.stream()
                .map(bi -> new BiValue<>(DomainType.valueOf(bi.getOne().byteValue()), bi.getTwo()))
                .collect(Collectors.toList());
    }
}
