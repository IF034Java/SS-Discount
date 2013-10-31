package com.springapp.mvc.service.implementation;

import com.springapp.mvc.entity.EnterpriseRatio;
import com.springapp.mvc.repository.IEntityRepository;
import com.springapp.mvc.repository.IEnterpriseRatioRepository;
import com.springapp.mvc.service.implementation.EntityService;
import com.springapp.mvc.service.IEnterpriseRatioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class EnterpriseRatioService extends EntityService<EnterpriseRatio> implements IEnterpriseRatioService {

    @Autowired
    private IEnterpriseRatioRepository enterpriseRatioRepository;

    @Override
    protected IEntityRepository<EnterpriseRatio> getRepository() {
        return enterpriseRatioRepository;
    }
    @Override
    public List<EnterpriseRatio> getTopList() {
       List<EnterpriseRatio> topList = enterpriseRatioRepository.getAll();
        for (int i = 0; i<topList.size(); i++){
            for (int j = 0; j<topList.size(); j++){
                if (!(i==j)) {
                    if (topList.get(i).getEnterprise().getId().equals(topList.get(j).getEnterprise().getId())){
                    topList.get(i).setValue(Math.round((topList.get(i).getValue()+topList.get(j).getValue())/2.0f));
                        topList.remove(j);
                        j--;
                    }
                }
            }
        }
        Collections.sort(topList, new Comparator<EnterpriseRatio>() {
            public int compare(EnterpriseRatio a1, EnterpriseRatio a2) {
                return a2.getValue() - a1.getValue();
            }
        });
        if (topList.size()>9) {
            for (int k = 10; k<topList.size(); k++){
                topList.remove(k);
                k--;
            }
        }

        return topList;
    }

    @Override
    public Boolean userAlreadyVote(Integer userId, Integer enterpriseId) {
        if (userId.equals(0)) {
        return true;
        }
        List<EnterpriseRatio> listEnterpriseRatio = getAll();
        for (int i = 0; i < listEnterpriseRatio.size(); i++) {
            EnterpriseRatio enterpriseRatio = listEnterpriseRatio.get(i);
            if ((enterpriseRatio.getUser().getId().equals(userId))&&
                    (enterpriseRatio.getEnterprise().getId().equals(enterpriseId)))
            {
         return true;
            }
        }

        return false;
    }
    @Override
    public Integer getVoteValue(Integer enterpriseId){
        List<EnterpriseRatio> listEnterpriseRatio = getAll();
        Integer count = 0;
        Integer value = 0;
        for (int i = 0; i < listEnterpriseRatio.size(); i++) {
            if (listEnterpriseRatio.get(i).getEnterprise().getId().equals(enterpriseId)){
              count++;
                value = value + listEnterpriseRatio.get(i).getValue();
            }
        }
        if (count==0) return 0;
    return  (Math.round(value/count));
    }
    @Override
    public Integer getVotes(Integer enterpriseId){
        List<EnterpriseRatio> listEnterpriseRatio = getAll();
        Integer count = 0;

        for (int i = 0; i < listEnterpriseRatio.size(); i++) {
            if (listEnterpriseRatio.get(i).getEnterprise().getId().equals(enterpriseId)){
                count++;

            }
        }
        return  count;
    }

}
