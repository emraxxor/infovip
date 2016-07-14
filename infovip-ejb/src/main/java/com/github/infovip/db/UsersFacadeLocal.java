/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.db;

import com.github.infovip.entities.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author attila
 */
@Local
public interface UsersFacadeLocal {

    void create(User users);

    void edit(User users);

    void remove(User users);

    User find(Object id);

    List<User> findAll();

    List<User> findRange(int[] range);

    User findUser(String userName, String password);

    int count();

}
