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

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.infovip.entities.User;  


/**
 *
 * Repository interface for {@link User} instances. Provides basic CRUD
 * operations due to the extension of {@link JpaRepository}.
 *
 *
 * @author attila
 */
@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Find the user with the given username. This method will be translated
     * into a query using the {@link javax.persistence.NamedQuery} annotation at
     * the {@link User} class.
     *
     * @param userName
     * @return
     */
    public User findByUserName(@Param("uname") String userName);

    /**
     * Find the user with the given username. This method will be translated
     * into a query using the {@link javax.persistence.NamedQuery} annotation at
     * the {@link User} class.
     *
     * @param userName Ignore case sensitive
     * @return
     */
    public User findByUserNameIgnoreCase(@Param("uname") String userName);

    /**
     * Find all users. The query can be limited and sorted by using the
     * "pageable".
     *
     *
     * @param pageable
     * @return
     */
    public Page<User> findAll(Pageable pageable);

    /**
     * Removes the users by its id
     *
     * @param uid
     * @return
     */
    List<User> removeByUserId(Long uid);
}
