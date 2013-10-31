package com.springapp.mvc.repository.implementation;

import com.springapp.mvc.entity.City;
import com.springapp.mvc.repository.implementation.EntityRepository;
import com.springapp.mvc.repository.ICityRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CityRepository extends EntityRepository<City> implements ICityRepository {

    protected CityRepository() {
        super(City.class);
    }
}
