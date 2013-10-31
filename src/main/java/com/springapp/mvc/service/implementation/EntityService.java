package com.springapp.mvc.service.implementation;

import com.springapp.mvc.repository.IEntityRepository;
import com.springapp.mvc.service.IEntityService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
public abstract class EntityService<Entity> implements IEntityService<Entity> {

    protected abstract IEntityRepository<Entity> getRepository();

    @Override
    public List<Entity> getAll() {
        return getRepository().getAll();
    }

    @Override
    public Entity get(int entityId) {
        return getRepository().get(entityId);
    }

    @Override
    public void delete(int entityId) {
        getRepository().delete(entityId);
    }

    @Override
    public void add(Entity entity) {
        getRepository().add(entity);
    }

    @Override
    public void update(Entity entity) {
        getRepository().update(entity);
    }

    @Override
    public List<Entity> getScope(int firstIndex, int maxNumberOfItemsOnPage) {
        return getRepository().getScope(firstIndex, maxNumberOfItemsOnPage);
    }
    @Override
     public int countItems(){
        return getRepository().countItems();
    }

    @Override
    public int numberOfPagesForPagination(int maxNumberOfItemsOnPage){
        if (countItems()%maxNumberOfItemsOnPage!=0){
            return countItems()/maxNumberOfItemsOnPage+1;
        } else{
            return countItems()/maxNumberOfItemsOnPage;
        }

    }
}
