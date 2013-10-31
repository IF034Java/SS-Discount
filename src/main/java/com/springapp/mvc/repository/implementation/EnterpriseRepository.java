package com.springapp.mvc.repository.implementation;

import com.springapp.mvc.entity.Enterprise;
import com.springapp.mvc.repository.implementation.EntityRepository;
import com.springapp.mvc.repository.IEnterpriseRepository;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class EnterpriseRepository extends EntityRepository<Enterprise> implements IEnterpriseRepository {

    protected EnterpriseRepository() {
        super(Enterprise.class);
    }

    @Override
    @Cacheable(value = "getEnterprisesBy" )
    public List<Enterprise> getAllBy(int categoryId, int townId, boolean orderByDiscount, boolean orderByRatio, int firstIndex, int numberOfItems) {
        Criteria criteria = getCurrentSession().createCriteria(getClazz());
        List<SimpleExpression> expressionList = new ArrayList<SimpleExpression>();
        List<Order> orderList = new ArrayList<Order>();
        expressionList.add(categoryId == 0 ? null : Restrictions.eq("category.id", categoryId));
        expressionList.add(townId == 0 ? null : Restrictions.eq("city.id", townId));
        orderList.add(orderByDiscount ? Order.asc("discountMax") : Order.desc("discountMax"));
        /*orderList.add(orderByRatio ? Order.asc("RATIO") : Order.desc("RATIO"));*/
        expressionList.removeAll(Collections.singleton(null));
        for (SimpleExpression expression : expressionList)
            criteria.add(expression);
        orderList.removeAll(Collections.singleton(null));
        for (Order order : orderList)
            criteria.addOrder(order);
        criteria.setFirstResult(firstIndex);
        criteria.setMaxResults(numberOfItems);
        return criteria.list();
    }

    @Override
    public List<Enterprise> getAllByCategory(int categoryId, int cityId) {

        return null;



      /* *//* return getCurrentSession().createQuery(String.format("from %s where CATEGORY_ID=%d",
                getClazz().getCanonicalName(),
                categoryId)).list();*//*
        if ((categoryId != 0) && (cityId != 0)){
            return getCurrentSession().createQuery(String.format("from %s where CATEGORY_ID=%d and CITY_ID=%d",
                    getClazz().getCanonicalName(),
                    categoryId, cityId)).list();
        }
        else if (categoryId != 0)
        {
             return getCurrentSession().createQuery(String.format("from %s where CATEGORY_ID=%d",
                     getClazz().getCanonicalName(),
                     categoryId)).list();
        }
        else if (cityId != 0)
        {
            return getCurrentSession().createQuery(String.format("from %s where CITY_ID=%d",
                    getClazz().getCanonicalName(), cityId)).list();
        }
        else
        {
            return getCurrentSession().createQuery(String.format("from %s",
                    getClazz().getCanonicalName())).list();
        }*/
    }

    @Override
    public List<Enterprise> getScopeByCategory(int firstIndex, int numberOfItems, int categoryId) {
        Query q = getCurrentSession().createQuery(String.format("from %s where CATEGORY_ID=%d",
                getClazz().getCanonicalName(),
                categoryId));
        q.setFirstResult(firstIndex);
        q.setMaxResults(numberOfItems);
        return q.list();
    }

    @Override
    @Cacheable(value = "countEnterpriseByCategory")
    public int countEnterpriseByCategory(int categoryId) {
        return ((Long) getCurrentSession().createQuery(String.format("select count (*) from %s where CATEGORY_ID=%d", getClazz().getCanonicalName(), categoryId)).uniqueResult()).intValue();
    }

    @Override
    @Cacheable(value = "countEnterprises")
    public int countEnterprises(int categoryId, int cityId) {
        Criteria criteria = getCurrentSession().createCriteria(getClazz()).setProjection(Projections.rowCount());
        List<SimpleExpression> expressionList = new ArrayList<SimpleExpression>();
        expressionList.add(categoryId == 0 ? null : Restrictions.eq("category.id", categoryId));
        expressionList.add(cityId == 0 ? null : Restrictions.eq("city.id", cityId));
        expressionList.removeAll(Collections.singleton(null));
        for (SimpleExpression expression : expressionList)
            criteria.add(expression);
        return ((Long) criteria.list().get(0)).intValue();
    }

}
