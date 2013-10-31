package com.springapp.mvc.repository.implementation;

import com.springapp.mvc.entity.ProposedEnterprise;
import com.springapp.mvc.repository.IProposedEnterpriseRepository;

public class ProposedEnterpriseRepository extends EntityRepository<ProposedEnterprise> implements IProposedEnterpriseRepository {

    protected ProposedEnterpriseRepository() {
        super(ProposedEnterprise.class);
    }
}
