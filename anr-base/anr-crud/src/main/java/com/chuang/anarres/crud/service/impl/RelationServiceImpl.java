package com.chuang.anarres.crud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.anarres.crud.entity.Relation;
import com.chuang.anarres.crud.enums.RelationType;
import com.chuang.anarres.crud.mapper.RelationMapper;
import com.chuang.anarres.crud.service.IRelationService;
import com.chuang.tauceti.support.exception.BusinessException;
import com.chuang.tauceti.tools.basic.reflect.BeanKit;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 关系表  服务实现类
 * </p>
 *
 * @author chuang
 * @since 2020-12-20
 */
@Service
public class RelationServiceImpl extends ServiceImpl<RelationMapper, Relation> implements IRelationService {
    @Override
    public void moveAndRise(Integer from, Integer to, RelationType type, @Nullable String condition1, @Nullable String condition2) {
        if (Objects.equals(from, to)) {
            throw new IllegalArgumentException("不能移动到自己下面!");
        }

        //1 获取 from 节点的所有祖节点。
        List<Integer> ancestors = deleteAndGetAllAncestor(from, type, condition1, condition2);

        if(ancestors.isEmpty()) {
            throw new BusinessException("不能移动根节点");
        }
        //2 获取 from 节点的所有子(孙)节点
        List<Integer> descendants = deleteAndGetAllDescendant(from, type, condition1, condition2);

        //3 修改所有 ancestor 为 ancestors集合， descendant为descendants集合的记录，并将距离减1
        baseMapper.subDistance(ancestors, descendants, type.getCode(), 1, condition1, condition2);

        //4 将from作为 descendant 转移到 to 下，且距离为1
        this.insertNode(from, to, type, condition1, condition2);
    }


    @Override
    public void moveTree(Integer from, Integer to, RelationType type, @Nullable String condition1, @Nullable String condition2) {
        if (Objects.equals(from, to)) {
            throw new IllegalArgumentException("不能移动到自己下面!");
        }
        // 检查 to 是不是 from 的子节点
        int count = count(new QueryWrapper<>(new Relation()
                .setDescendant(to)
                .setAncestor(from)
                .setType(type)
                .setCondition1(condition1)
                .setCondition2(condition2)
        ));
        if(count > 0) {
            throw new BusinessException("使用移动并删除子节点的操作，不能将父节点移动到子节点下");
        }

        // 1，获取 ancestor为 from 的所有关系集合 (被移动的树关系)
        List<Relation> fromDescendants = findAllDescendant(from, type, condition1, condition2);


        // 2，把from这棵树的所有节点关系都删除，解除from这棵树和其他节点的关系。 （删掉旧关系）
        List<Integer> descendants = fromDescendants.stream().map(Relation::getDescendant).collect(Collectors.toList());
        descendants.add(from);
        deleteByDescendants(descendants, type, condition1, condition2);

        // 3, 通过方法快速建立 from 和 to的关系。
        insertNode(from, to, type, condition1, condition2);

        // 4.查出 descendant 为 from 的所有关系 ,用于把from的子节点挂上去建立关系。
        List<Relation> fromNewAncestors = findAllAncestor(from, type, condition1, condition2);

        // 5. 建立关系
        // 5.1 加入from的旧关系
        List<Relation> relations = new ArrayList<>(fromDescendants);

        // 5.2 将from新的 ancestor 和 from旧的 descendant 建立关系
        for(Relation fromNewAncestor: fromNewAncestors) {
            for(Relation fromDescendant: fromDescendants) {
                Relation copy = BeanKit.copyOne(fromNewAncestor);
                copy.setDistance(fromNewAncestor.getDistance() + fromDescendant.getDescendant())
                        .setDescendant(fromDescendant.getDescendant());
                relations.add(copy);
            }
        }

        saveBatch(relations);
    }

    private List<Integer> deleteAndGetAllAncestor(Integer descendant, RelationType type, @Nullable String condition1, @Nullable String condition2) {
        Relation condition = new Relation();
        condition.setDescendant(descendant);
        condition.setType(type);
        condition.setCondition1(condition1);
        condition.setCondition2(condition2);

        List<Integer> ancestors = list(condition)
                .stream().map(Relation::getAncestor).collect(Collectors.toList());
        if(!ancestors.isEmpty()) {
            remove(condition);
        }
        return ancestors;
    }

    private List<Integer> deleteAndGetAllDescendant(Integer ancestor, RelationType type, @Nullable String condition1, @Nullable String condition2) {
        List<Integer> descendants = findAllDescendant(ancestor, type, condition1, condition2)
                .stream().map(Relation::getDescendant).collect(Collectors.toList());
        delete(ancestor, type, condition1, condition2);
        return descendants;
    }
}
