/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jgl.bookwebapp.model;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author L117student
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEM();

    public void create(T entity) {
        getEM().persist(entity);
    }

    public void edit(T entity) {
        getEM().merge(entity);
    }

    public void remove(T entity) {
        getEM().remove(getEM().merge(entity));
    }

    public T find(Object id) {
        return getEM().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEM().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEM().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEM().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEM().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEM().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEM().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEM().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
