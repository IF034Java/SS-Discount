package com.springapp.mvc.repository.implementation;

import com.springapp.mvc.repository.IEntityRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;


public abstract class EntityRepository<Entity> implements IEntityRepository<Entity> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<Entity> clazz;

    private void setClazz(Class<Entity> clazz) {
        this.clazz = clazz;
    }

    protected EntityRepository(Class<Entity> clazz){
        setClazz(clazz);
    }

    @Override
    public Class<Entity> getClazz() {
        return this.clazz;
    }

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession().isOpen()
                ? sessionFactory.getCurrentSession()
                : sessionFactory.openSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Entity> getAll() {
        return getCurrentSession().createQuery(String.format("from %s",getClazz().getCanonicalName())).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Entity get(int entityId) {
        return (Entity) getCurrentSession().get(getClazz(), entityId);
    }

    @Override
    @CacheEvict(value = {"countEnterpriseByCategory", "countEnterprises",
                            "countCategory", "getEnterprisesBy"}, allEntries = true)
    public void delete(int entityId) {
        Entity entity = get(entityId);
        if (entity != null)
            getCurrentSession().delete(entity);
    }

    @Override
    @CacheEvict(value = {"countEnterpriseByCategory", "countEnterprises",
            "countCategory", "getEnterprisesBy"}, allEntries = true)
    public void add(Entity entity) {
        getCurrentSession().save(entity);
    }

    @Override
    @CacheEvict(value = {"countEnterpriseByCategory", "countEnterprises",
            "countCategory", "getEnterprisesBy"}, allEntries = true)
    public void update(Entity entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public List<Entity> getScope(int firstIndex, int numberOfItems) {
        Query q = getCurrentSession().createQuery(String.format("from %s", getClazz().getCanonicalName()));
        q.setFirstResult(firstIndex);
        q.setMaxResults(numberOfItems);
        return q.list();
    }

    @Override
    public int countItems() {
    return ((Long)getCurrentSession().createQuery(String.format("select count (*) from %s", getClazz().getCanonicalName())).uniqueResult()).intValue();
    }
}
