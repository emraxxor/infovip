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
package com.github.infovip.test.elasticsearch;

import com.github.infovip.core.elasticsearch.DefaultDateFormatter;
import com.github.infovip.spring.elasticsearch.entities.TimelinePostEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
import static org.elasticsearch.index.query.QueryBuilders.*;
import org.joda.time.DateTime;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

/**
 *
 * @author attila
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContextTest.xml"})
@ActiveProfiles("dev")
public class ElasticsearchTest {

    @Autowired
    private ElasticsearchTemplate template;

    @Test
    public void searchQuery() {
        List sort = new ArrayList();
        TimelinePostEntity ob = new TimelinePostEntity(new DateTime().toDate(), 25L, "testUser", "Message", "simple");
        sort.add(SortBuilders.fieldSort("creationTime").order(SortOrder.DESC));
        Calendar cal = Calendar.getInstance();
        String from = DefaultDateFormatter.format(cal.getTime(), DefaultDateFormatter.PATTERN.CHUNK_DATE);
        cal.add(Calendar.DATE, 1);
        String to = DefaultDateFormatter.format(cal.getTime(), DefaultDateFormatter.PATTERN.CHUNK_DATE);

        IndexQuery q = new IndexQuery();
        q.setObject(ob);
        template.index(q);

        assertNotEquals(null, ob.getId());

        SearchQuery sq = new NativeSearchQuery(
                boolQuery()
                .must(termQuery("userName", "testUser")),
                QueryBuilders.rangeQuery("creationTime").from(from).to(to), sort);

        List<TimelinePostEntity> result = template.queryForList(sq, TimelinePostEntity.class);
        assertNotEquals(0, result);
    }
}
