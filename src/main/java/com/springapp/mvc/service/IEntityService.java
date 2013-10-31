package com.springapp.mvc.service;

import java.util.List;

public interface IEntityService<Entity> {

    public List<Entity> getAll();
    public Entity get(int entityId);
    public void delete(int entityId);
    public void add(Entity entity);
    public void update(Entity entity);
    public List<Entity> getScope(int firstIndex, int maxNumberOfItemsOnPage);
    public int countItems();
    public int numberOfPagesForPagination(int maxNumberOfItemsOnPage);
}
