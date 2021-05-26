package com.chuang.anarres.service.single;

import com.chuang.anarres.enums.ProposalStatus;
import com.chuang.anarres.model.SeoDomainProposal;
import com.chuang.tauceti.rowquery.IRowQueryService;
import com.chuang.tauceti.support.exception.BusinessException;

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
public interface ISeoDomainProposalService extends IRowQueryService<SeoDomainProposal> {


    default Set<Integer> lockBatch(Set<Integer> ids, String username) {
        lambdaUpdate()
                .set(SeoDomainProposal::getState, ProposalStatus.RECEIPT)
                .set(SeoDomainProposal::getAuditor, username)
                .in(SeoDomainProposal::getId, ids)
                .update();

        return lambdaQuery().select(SeoDomainProposal::getId)
                .eq(SeoDomainProposal::getState, ProposalStatus.RECEIPT)
                .eq(SeoDomainProposal::getAuditor, username)
                .in(SeoDomainProposal::getId, ids)
                .list().stream().map(SeoDomainProposal::getId)
                .collect(Collectors.toSet());

    }

    default boolean lock(Integer id, String username) {
        SeoDomainProposal proposal = findById(id).orElseThrow(() -> new BusinessException(-1, "找不到这条记录"));

        if(proposal.getState() == ProposalStatus.RECEIPT) {
            if(username.equals(proposal.getAuditor())) {
                return true;
            } else {
                throw new BusinessException(-1, "这个记录别人正在处理");
            }
        }

        if(proposal.getState() == ProposalStatus.CREATED) {
            proposal.setState(ProposalStatus.RECEIPT)
                    .setAuditor(username);
            return updateById(proposal);
        } else {
            throw new BusinessException(-1, "这个记录已经审核，不能重复审核");
        }
    }

}
