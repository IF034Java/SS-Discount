package com.springapp.mvc.service.implementation;

import com.springapp.mvc.entity.City;
import com.springapp.mvc.repository.IEntityRepository;
import com.springapp.mvc.repository.ICityRepository;
import com.springapp.mvc.service.implementation.EntityService;
import com.springapp.mvc.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CityService extends EntityService<City> implements ICityService {

    @Autowired
    private ICityRepository categoryRepository;

    @Override
    protected IEntityRepository<City> getRepository() {
        return categoryRepository;
    }
}
