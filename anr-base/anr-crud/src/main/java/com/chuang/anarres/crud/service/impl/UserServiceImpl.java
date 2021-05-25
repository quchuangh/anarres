package com.chuang.anarres.crud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.anarres.crud.entity.Organization;
import com.chuang.anarres.crud.entity.Position;
import com.chuang.anarres.crud.entity.User;
import com.chuang.anarres.crud.enums.RoleType;
import com.chuang.anarres.crud.mapper.UserMapper;
import com.chuang.anarres.crud.service.IOrganizationService;
import com.chuang.anarres.crud.service.IPositionService;
import com.chuang.anarres.crud.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户  服务实现类
 * </p>
 *
 * @author chuang
 * @since 2021-05-21
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource private IOrganizationService organizationService;
    @Resource private IPositionService positionService;

    @Override
    public boolean assignRole(String username, List<String> roles) {
        baseMapper.deleteAllRole(username);
        baseMapper.assignRole(username, RoleType.USER_ROLE.getCode(), roles);
        return true;
    }

    @Override
    public boolean resetJoinGroup(String username, List<String> organizationCodes) {
        List<String> oldPositions = positionService.findByUser(username)
                .stream()
                .map(Position::getPositionCode)
                .collect(Collectors.toList());

        baseMapper.deleteAllOrganization(username);

        if(!organizationCodes.isEmpty()) {
            baseMapper.joinGroup(username, organizationCodes);
        }
        if(oldPositions.isEmpty()) {
            return true;
        } else {
            return resetAppointment(username, oldPositions);
        }
    }

    @Override
    public boolean resetAppointment(String username, List<String> positionCodes) {
        baseMapper.deleteAllPosition(username);
        List<Organization> joined = organizationService.findJoined(username);
        if(joined.isEmpty()) {
            log.warn("{}没有任何组织，却要分配职位", username);
            return false;
        }

        if(positionCodes.isEmpty()) {
            return true;
        }

        List<String> joinedOrgCodes = joined.stream().map(Organization::getCode).collect(Collectors.toList());
        List<Position> validPositions = positionService.findByOrg(joinedOrgCodes);

        List<String> contains = validPositions.stream()
                .filter(position -> positionCodes.contains(position.getPositionCode()))
                .map(Position::getPositionCode)
                .collect(Collectors.toList());

        if(contains.isEmpty()) {
            log.warn("给{}分配的职位没有一个正确的", username);
            return false;
        }

        return baseMapper.appointment(username, contains) > 0;
    }
}
