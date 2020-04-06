/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.ejb; 

import com.sfp.model.CauDepartamentos;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.Query;

/**
 *
 * @author jleal
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    public List<T> Consulta(String Consulta, String Nocredencial){
         EntityManager em = getEntityManager(); 
         //em.setFlushMode(FlushModeType.COMMIT);
         Query query=em.createQuery(Consulta); 
         //em.setFlushMode(FlushModeType.COMMIT);
         if (Nocredencial.length() != 0){
           query.setParameter(1, Nocredencial);   
         }
         return query.getResultList();
    }
    public List<T> Consulta_2(String Consulta, Date Nocredencial){
         Query query=getEntityManager().createQuery(Consulta); 
         if (Nocredencial != null){
           query.setParameter(1, Nocredencial);   
         }
         return query.getResultList();
    }
    
    public List<T> Consulta_3(String Consulta, int Nocredencial){
         Query query=getEntityManager().createQuery(Consulta); 
         if (Nocredencial != 0){
           query.setParameter(1, Nocredencial);   
         }
         return query.getResultList();
    }
    
    public List<T> Consulta_4(String Consulta, Date fechainicial, Date fechafinal ){
         Query query=getEntityManager().createQuery(Consulta); 
         if (fechainicial != null){
           query.setParameter(1, fechainicial);   
           query.setParameter(2, fechafinal);   
         }
         return query.getResultList();
    }
    
    public List<T> Consulta_5(String Consulta, Date fechainicial, int Nocredencial ){
         Query query=getEntityManager().createQuery(Consulta); 
         if (fechainicial != null){
           query.setParameter(1, fechainicial);   
           query.setParameter(2, Nocredencial);   
         }
         return query.getResultList();
    }
}
