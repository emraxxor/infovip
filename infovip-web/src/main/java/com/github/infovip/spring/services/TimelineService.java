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

import com.github.infovip.spring.elasticsearch.entities.TimelinePostEntity;
import com.github.infovip.spring.elasticsearch.repositories.TimelineRepository;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author attila
 */
@Service
public class TimelineService implements Serializable {

    @Autowired
    private TimelineRepository timelineRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public TimelineService() {
    }

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
