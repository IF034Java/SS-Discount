package com.springapp.mvc.service.implementation;

import com.springapp.mvc.entity.Category;
import com.springapp.mvc.repository.IEntityRepository;
import com.springapp.mvc.repository.ICategoryRepository;
import com.springapp.mvc.service.implementation.EntityService;
import com.springapp.mvc.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryService extends EntityService<Category> implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    protected IEntityRepository<Category> getRepository() {
       return categoryRepository;
    }
}
