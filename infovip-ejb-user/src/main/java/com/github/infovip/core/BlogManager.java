package com.github.infovip.core;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.github.infovip.entities.UserBlog;

/**
 * 
 * @author Attila Barna
 *
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class BlogManager  {
	
    @Resource
    private UserTransaction userTransaction;

    @EJB
    private UserManager um;
    
    @PersistenceContext
    protected EntityManager em;

    public UserBlog save(UserBlog ub) {
    	if ( ub.getBid() == null ) 
    		em.persist(ub);
    	else
    		ub = em.merge(ub);
    	return ub;
    }
    
    public UserBlog findById(Long id) {
    	return em.find(UserBlog.class, id);
    }
    
    public List<UserBlog> findAll(long uid, int offset, int limit) {
		CriteriaBuilder criteriaBulder = em.getCriteriaBuilder();
		CriteriaQuery<UserBlog> criteriaQuery = criteriaBulder.createQuery(UserBlog.class);
		Root<UserBlog> root = criteriaQuery.from(UserBlog.class);
		Predicate p = criteriaBulder.equal(root.get("user"), um.findById(uid) );
		criteriaQuery.where(p);
		return em.createQuery(criteriaQuery).setFirstResult(offset).setMaxResults(limit).getResultList();
    }
    
    public UserBlog update(UserBlog ub) {
		try {
			userTransaction.begin();
			ub = save(ub);
			userTransaction.commit();
		} catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
			try {
				userTransaction.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
		}
		return ub;
    }
    
    public UserBlog createBlog(Long userId, String name) {
    	UserBlog ub = null;
    	try {
			userTransaction.begin();
			save(ub=new UserBlog(um.findById(userId), name));
			userTransaction.commit();
		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
			try {
				userTransaction.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
		}
    	return ub;
    }
    
}
