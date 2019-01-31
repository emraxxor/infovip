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

/**
 *
 * @author attila
 */
@Document(indexName = "timeline", type = "post")
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

    public TimelinePostEntity() {
    }

    public TimelinePostEntity(Date creationTime, Long userId, String userName, String message, String postType) {
        this.creationTime = creationTime;
        this.userId = userId;
        this.userName = userName;
        this.message = message;
        this.postType = postType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.creationTime);
        hash = 19 * hash + Objects.hashCode(this.userId);
        hash = 19 * hash + Objects.hashCode(this.userName);
        hash = 19 * hash + Objects.hashCode(this.message);
        hash = 19 * hash + Objects.hashCode(this.postType);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TimelinePostEntity other = (TimelinePostEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.postType, other.postType)) {
            return false;
        }
        if (!Objects.equals(this.creationTime, other.creationTime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TimelinePostEntity{" + "id=" + id + ", creationTime=" + creationTime + ", userId=" + userId + ", userName=" + userName + ", message=" + message + ", postType=" + postType + '}';
    }

}
