package com.springapp.mvc.repository;

import com.springapp.mvc.entity.Enterprise;
import com.springapp.mvc.repository.IEntityRepository;

import java.util.List;

public interface IEnterpriseRepository extends IEntityRepository<Enterprise> {

    public List<Enterprise> getAllBy(int categoryId,
                                     int townId,
                                     boolean orderByDiscount,
                                     boolean orderByRatio,
                                     int firstIndex,
                                     int numberOfItems);

    List<Enterprise> getAllByCategory(int categoryId, int cityId);

    List<Enterprise> getScopeByCategory(int firstIndex, int numberOfItems, int categoryId);

    int countEnterpriseByCategory(int categoryId);

    int countEnterprises(int categoryId, int cityId);
}
