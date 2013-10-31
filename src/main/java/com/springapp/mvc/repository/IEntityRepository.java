package com.springapp.mvc.repository;

import org.hibernate.Session;


import java.util.List;


public interface IEntityRepository<Entity> {

    public Class<Entity> getClazz();

    public Session getCurrentSession();

    public void delete(int entityId);

    public void add(Entity entity);

    public void update(Entity entity);

    @SuppressWarnings("unchecked")
    public Entity get(int entityId);

    @SuppressWarnings("unchecked")
    public List<Entity> getAll();

    @SuppressWarnings("unchecked")
    public List<Entity> getScope(int firstIndex, int numberOfItems);

    @SuppressWarnings("unchecked")
    public int countItems();
}
