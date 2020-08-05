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
package com.github.infovip.spring.services;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import com.github.infovip.spring.elasticsearch.entities.TimelinePostEntity;
import com.github.infovip.spring.elasticsearch.repositories.TimelineRepository;

/**
 *
 * @author attila
 */
@Service
public class TimelineService implements Serializable {

    @Autowired
    private TimelineRepository timelineRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    public TimelineService() {
    }

    /**
     * Removes the given entity
     * @param e
     */
    public void delete(TimelinePostEntity e) {
    	timelineRepository.delete(e);
    }

    public Long count() {
    	return timelineRepository.count();
    }
    
    /**
     * Checks if the given entity exists
     * @return
     */
    public boolean exists(String id) {
    	return timelineRepository.existsById(id);
    }
    
    /**
     * Saves the current entity
     * @param e
     * @return
     */
    public TimelinePostEntity save(TimelinePostEntity e) {
        timelineRepository.save(e);
        return e;
    }

    public Iterable<TimelinePostEntity> findAll() {
        return timelineRepository.findAll();
    }

    public Page<TimelinePostEntity> findAll(Pageable pgbl) {
        return timelineRepository.findAll(pgbl);
    }

}
