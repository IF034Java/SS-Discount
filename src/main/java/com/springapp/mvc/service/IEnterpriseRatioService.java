package com.springapp.mvc.service;

import com.springapp.mvc.entity.EnterpriseRatio;
import com.springapp.mvc.service.IEntityService;

import java.util.List;

public interface IEnterpriseRatioService extends IEntityService<EnterpriseRatio> {
    public Boolean userAlreadyVote(Integer userId, Integer enterpriseId);
    public List<EnterpriseRatio> getTopList();
    public Integer getVoteValue(Integer enterpriseId);
    public Integer getVotes(Integer enterpriseId);
}
