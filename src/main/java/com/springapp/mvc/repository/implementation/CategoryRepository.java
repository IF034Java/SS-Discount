package com.springapp.mvc.repository.implementation;

import com.springapp.mvc.entity.Category;
import com.springapp.mvc.repository.implementation.EntityRepository;
import com.springapp.mvc.repository.ICategoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository extends EntityRepository<Category> implements ICategoryRepository {

    protected CategoryRepository() {
        super(Category.class);
    }
}
