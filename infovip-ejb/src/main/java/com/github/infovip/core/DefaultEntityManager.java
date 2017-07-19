package com.github.infovip.core;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.validator.Validator;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

/**
 * DefaultEntityManager helps to manage the entities.
 * <p>
 * Using DefaultEntityManager gives us the opportunity to handle simple
 * transactions. Complicated transactions must be implemented manually.
 * <p>
 * By default, the typical persistence operations (merge,persist,remove,find)
 * are supported by class, but they can only be used if the transactions are
 * managed by the Container. Bean managed transactions should be implemented
 * manually.
 *
 * @author attila
 */
public abstract class DefaultEntityManager<T> {

    /**
     * Current entity
     */
    private Class<T> entityClass;

    /**
     * Simple markers for simple transactions
     */
    public enum PersistenceOperation {

        /**
         * Merge the state of the given entity into the current persistence
         * context.
         */
        MERGE,
        /**
         * Make an entity instance managed and persistent.
         */
        PERSIST,
        /**
         * Remove the entity instance.
         */
        REMOVE,
        /**
         * Find the entity instance by primary key
         */
        FIND
    }

    @PersistenceContext
    protected EntityManager em;

    /**
     * Helps to create Criteria based queries
     */
    protected CriteriaBuilder cb;

    public DefaultEntityManager(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @PostConstruct
    public void postConstruct() {
        cb = getEntityManager().getCriteriaBuilder();
    }

    protected EntityManager getEntityManager() {
        return this.em;
    }

    /**
     * It executes a simple transaction. It is used for simple operations such
     * as modifying, merging, removing entities.
     *
     * @param <T1>
     * @param u
     * @return
     */
    protected <T1> T1 transaction(T1 u, UserTransaction ut, PersistenceOperation o, boolean flush) {
        try {
            ut.begin();
            em.joinTransaction();
            switch (o) {
                case PERSIST:
                    em.persist(u);
                    break;
                case MERGE:
                    u = em.merge(u);
                    break;
                case REMOVE:
                    u = em.merge(u);
                    em.remove(u);
                    break;
                default:
                    break;
            }

            if (flush) {
                em.flush();
            }
        } catch (RuntimeException e) {
            Logger.getLogger(DefaultEntityManager.class.getName()).log(Level.SEVERE, null, e);
        } catch (NotSupportedException | SystemException e) {
            Logger.getLogger(DefaultEntityManager.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                ut.commit();
            } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException ex) {
                try {
                    ut.rollback();
                } catch (IllegalStateException | SecurityException | SystemException ex1) {
                    Logger.getLogger(DefaultEntityManager.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }

        return u;
    }

    /**
     * Gets the current bean name.
     *
     * @return
     */
    public abstract String getBeanName();

    /**
     * Validates the given entity
     *
     * @param entity
     * @return
     */
    public boolean validate(T entity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        if (constraintViolations.size() > 0) {
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<T> cv = iterator.next();
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "The following bean is not valid : ");
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, (cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " " + cv.getMessage()));
            }
            return false;
        }
        return true;
    }

    /**
     * Persists the entity. Actually this method can only be used if the
     * transaction management is set to container managed transaction
     *
     * @param entity
     */
    public void create(T entity) {
        if (validate(entity)) {
            getEntityManager().persist(entity);
        }
    }

    /**
     * Merges the given entity. Actually this method can only be used if the
     * transaction management is set to container managed transaction
     *
     * @param entity
     */
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    /**
     * Removes the given entity. Actually this method can only be used if the
     * transaction management is set to container managed transaction
     *
     * @param entity
     */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     * Finds the entityClass using primary key It can only be used if there is a
     * primary key defined in the bean
     *
     * @param id
     * @return
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    
    /**
     * Returns with all entities
     *
     * @return
     */
    public List<T> findAll() {
        CriteriaQuery cq = cb.createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Limits the entities
     *
     * @param range
     * @return
     */
    public List<T> findRange(int[] range) {
        CriteriaQuery cq = cb.createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /**
     * Entities count
     *
     * @return
     */
    public int count() {
        CriteriaQuery cq = cb.createQuery();
        Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
