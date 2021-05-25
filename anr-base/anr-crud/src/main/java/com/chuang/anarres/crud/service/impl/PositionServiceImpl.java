package com.chuang.anarres.crud.service.impl;

import com.chuang.anarres.crud.entity.Position;
import com.chuang.anarres.crud.mapper.PositionMapper;
import com.chuang.anarres.crud.service.IPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 职位表; 服务实现类
 * </p>
 *
 * @author chuang
 * @since 2021-05-24
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {

    @Override
    public List<Position> findByUser(String username) {
        return baseMapper.findByUser(username);
    }
}
