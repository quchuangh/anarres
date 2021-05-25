package com.chuang.anarres.crud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuang.anarres.crud.entity.Relation;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Nullable;
import java.util.List;

/**
 * <p>
 * 关系表  Mapper 接口
 * </p>
 *
 * @author chuang
 * @since 2020-12-20
 */
public interface RelationMapper extends BaseMapper<Relation> {
    /**
     * 将ancestor为ancestors集合且 descendant为 descendants 集合的记录距离减1
     * @param ancestors 祖节点
     * @param descendants 子节点
     * @param type  关系类型
     * @param condition1 附加条件1 可为空
     * @param condition2 附加条件2 可为空
     */
    void subDistance(@Param("ancestors") List<Integer> ancestors,
                     @Param("descendants") List<Integer> descendants,
                     @Param("type") int type,
                     @Param("distance") Integer distance,
                     @Param("condition1") @Nullable String condition1,
                     @Param("condition2") @Nullable String condition2);
}
