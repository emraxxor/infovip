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
package com.github.infovip.spring.elasticsearch.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author attila
 */
@Document(indexName = "timeline")
@Getter
@Setter
@NoArgsConstructor
public class TimelinePostEntity implements Serializable {

    @Id
    private String id;

    @JsonProperty("userId")
    @Field(type = FieldType.Long)
    private Long userId;

    @JsonProperty("userName")
    @Field(type = FieldType.Keyword)
    private String userName;

    @JsonProperty("message")
    @Field(type = FieldType.Text, analyzer="standard", searchAnalyzer="standard")
    private String message;

    @JsonProperty("postType")
    @Field(type = FieldType.Keyword)
    private String postType;

    @JsonProperty("creationTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    @Field(type = FieldType.Date,format=DateFormat.custom,pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    private Date creationTime;

    public TimelinePostEntity(Date creationTime, Long userId, String userName, String message, String postType) {
        this.creationTime = creationTime;
        this.userId = userId;
        this.userName = userName;
        this.message = message;
        this.postType = postType;
    }


}
