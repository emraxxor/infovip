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
package com.github.infovip.spring.repositories;

import com.github.infovip.entities.LogRegistration;
import com.github.infovip.entities.User;
import java.util.Date;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author attila
 */
@RunWith(Arquillian.class)
public class UserRepositoryTest {

    @PersistenceContext
    private EntityManager em;
    
    @Resource
    private UserTransaction utx;

    /**
     * User Repository
     */
    private CrudRepository<User, Long> userRepository;


    @Deployment
    public static Archive<?> createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addClasses(
                        UserRepository.class
                )
                .addPackage("com.github.infovip.entities")
                .addAsManifestResource("glassfish-resources.xml", "glassfish-resources.xml")
                .addAsManifestResource("test-persistence.xml", "persistence.xml")
                .addAsManifestResource("spring-data.xml", "spring-data.xml")
                .addAsResource("spring-data.xml");

        return jar;
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        userRepository = new SimpleJpaRepository(User.class, em);
    }

    @After
    public void tearDown() {
    }

    /**
     * Create an instance of User for testing
     *
     * @param userName
     * @return
     */
    private User createUser(String userName) {
        User u = new User(0L);
        u.setUserName(userName);
        u.setUserMail("testmail@mail.com");
        u.setUserPassword("password");
        LogRegistration lg = new LogRegistration(0L, "127.0.0.1", new Date(System.currentTimeMillis()));
        u.setLogRegistration(lg);
        lg.setUid(u);
        return u;
    }

    @Test
    public void saveUser() throws Exception {
        utx.begin();
        em.joinTransaction();
        User user = createUser("testUser");
        user = userRepository.save(user);
        utx.commit();
        
        assertEquals(user, userRepository.findOne(user.getUserId()));
    }
}
