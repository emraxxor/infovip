package com.github.infovip.test;

import com.github.infovip.entities.LogRegistration;
import com.github.infovip.entities.User;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

/*
 * Copyright (C) 2016 attila
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * Simple test for testing the default database and its entities.
 *
 * @todo Remaining functions must be implemented.
 * @author attila
 */
@RunWith(Arquillian.class)
public class UserPersistenceTest {

    @Deployment
    public static Archive<?> createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addPackage(LogRegistration.class.getPackage())
                .addPackage(User.class.getPackage())
                .addAsManifestResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource("test-persistence.xml", "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        return jar;
    }

    private static final Map<String, Object> USER_DATA = new HashMap();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    static {
        USER_DATA.put("name", "testUserName");
        USER_DATA.put("mail", "testuser@testmail.com");
        USER_DATA.put("password", "password");
    }

    @PersistenceContext
    EntityManager em;

    @Resource
    private UserTransaction utx;

    @Before
    public void preparePersistenceTest() throws Exception {
        clearData();
    }

    /**
     * Gets the User by its name.
     *
     *
     * @param name
     * @return If the user doesn't exists then null will be returned
     */
    private User findUserByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<User> u = cq.from(User.class);
        cq.select(u).where(cb.equal(u.get("userName"), name));
        try {
            return (User) em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    private void clearData() throws Exception {
        utx.begin();
        em.joinTransaction();
        User u = findUserByName((String) USER_DATA.get("name"));
        if (u != null) {
            em.remove(u);
        }
        utx.commit();
    }

    private User createUser() throws Exception {
        utx.begin();
        em.joinTransaction();
        User u = new User(0L);
        u.setUserMail((String) USER_DATA.get("mail"));
        u.setUserName((String) USER_DATA.get("name"));
        u.setUserPassword((String) USER_DATA.get("password"));
        LogRegistration lg = new LogRegistration(0L, "127.0.0.1", new Date(System.currentTimeMillis()));
        u.setLogRegistration(lg);
        lg.setUid(u);
        em.persist(u);
        em.flush();
        utx.commit();
        return u;
    }

    private void removeUser(User u) throws Exception {
        utx.begin();
        em.joinTransaction();
        u = em.merge(u);
        em.remove(u);
        em.flush();
        utx.commit();
    }

    @After
    public void after() throws Exception {
        clearData();
    }

    @Test
    public void shouldFindGivenUser() throws Exception {
        createUser();
        User u = findUserByName((String) USER_DATA.get("name"));
        Assert.assertNotNull(true);
        removeUser(u);
        em.clear();
    }

}
