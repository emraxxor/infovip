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
package com.github.infovip.beans.stateless.user;

import com.github.infovip.core.DefaultEntityManager;
import com.github.infovip.entities.LogRegistration;
import com.github.infovip.entities.User;
import com.github.infovip.spring.services.UserService;
import java.util.Date;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.matchers.JUnitMatchers;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * A simple test for testing functions of UserManagement. Actually its a bit
 * funny, I have tried to exclude the default glassfish-resource.xml file during
 * the test and I've tried to get maven to exclude the default files and only
 * include files that can be found under the test directory. Actually, I've
 * already wasted a lot of time with searching, and I didn't want to continue
 * it. I just googled for almost five hours to know how the default
 * configurations can be excluded. And 5 hours is a bit lot for me, so I will
 * continue it if I will have a free time. This test only can be used if the
 * default persistence file is modified to use a test database otherwise the
 * tests will run on the product database, so it is not recommended to run the
 * this test by default.
 * 
 * 
 * The above bug has been fixed.
 *
 * @todo Remaining functions must be implemented.
 * @author attila
 */
@RunWith(Arquillian.class)
public class UserManagementTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @EJB
    private UserManagementLocal userManagement;

    /**
     * Current user service
     */
    UserService userService;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        userService = userManagement.getUserService();
    }

    @After
    public void tearDown() {
    }

    @Deployment
    public static Archive<?> createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addClasses(
                        UserManagementLocal.class,
                        UserManagement.class,
                        DefaultEntityManager.class
                )
                .addPackage("com.github.infovip.entities")
                .addPackage("com.github.infovip.spring.services")
                .addPackage("com.github.infovip.repositories")
                .addAsManifestResource("glassfish-resources.xml", "glassfish-resources.xml")
                .addAsManifestResource("test-persistence.xml", "persistence.xml")
                .addAsResource("beanRefContext.xml")
                .addAsResource("spring-data.xml");

        return jar;
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

    /**
     * Test of findUserByName method, of class UserManagement.
     */
    @Test
    public void testFindUserByName() throws Exception {
        System.out.println("findUserByName");
        String uname = "testUserName";
        User expResult = createUser(uname);
        expResult = userService.save(expResult);
        User result = userService.findByUserName(uname);
        assertEquals(expResult, result);
        System.out.println(expResult.getUserId());
        userService.delete(result);
    }

    /**
     *
     */
    @Test
    public void testRepository() {
        User expResult = createUser("testUserName");
        expResult = userService.save(expResult);
        Pageable page = new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "userName"));
        Page<User> data = userService.findAll(page);
        assertThat(data, JUnitMatchers.hasItem(expResult));
        userService.delete(expResult);
    }

}
