/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.db;

import com.github.infovip.AbstractFacade;
import com.github.infovip.entities.User;
import com.github.infovip.util.BasicUtilities;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author attila
 */
@Stateless
public class UsersFacade extends AbstractFacade<User> implements UsersFacadeLocal {

    @PersistenceContext(unitName = "com.github.infovip.persistence")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(User.class);
    }

    @Override
    public User findUser(String userName, String password) {
        try {
            return (User) em.createNamedQuery("User.findByNameAndUpassword")
                    .setParameter("uname", userName)
                    .setParameter("upassword", BasicUtilities.getMD5(password))
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
