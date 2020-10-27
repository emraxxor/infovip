package com.github.infovip.spring.elasticsearch.entities;

import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.infovip.core.data.ESDefaultJoinRelation;
import com.github.infovip.web.application.es.activity.ActivityJoinType;

import lombok.Getter;
import lombok.Setter;

@Document(indexName = "activity-post")
@Getter
@Setter
public class ActivityLikeEntity {

    @Id
    private String id;

    @JsonProperty("uid")
    @Field(type = FieldType.Long)
    private Long uid;
    
    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @Field(type = FieldType.Date,format=DateFormat.custom,pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date creationTime;


    @JsonProperty("doc")
    @Field(type = FieldType.Keyword)
    private String doc;
    
    @JsonProperty("join")
    @Field(type=FieldType.Auto)
    private ESDefaultJoinRelation join;
    
    
    public ActivityLikeEntity() {}


	public ActivityLikeEntity(String id, Long uid, Date creationTime, String doc, ActivityJoinType type) {
		super();
		this.id = id;
		this.uid = uid;
		this.creationTime = creationTime;
		this.doc = doc;
		this.join = new ESDefaultJoinRelation(type.value(), doc);
	}


	public ActivityLikeEntity(Long uid, String doc, ActivityJoinType type) {
		super();
		this.uid = uid;
		this.creationTime = new DateTime().toDate();
		this.doc = doc;
		this.join = new ESDefaultJoinRelation(type.value(), doc);
	}
    
}
