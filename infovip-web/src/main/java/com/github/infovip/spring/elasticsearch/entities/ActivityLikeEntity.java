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

@Document(indexName = "activity-post", type = "data")
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


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the uid
	 */
	public Long getUid() {
		return uid;
	}


	/**
	 * @param uid the uid to set
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}


	/**
	 * @return the creationTime
	 */
	public Date getCreationTime() {
		return creationTime;
	}


	/**
	 * @param creationTime the creationTime to set
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}


	/**
	 * @return the doc
	 */
	public String getDoc() {
		return doc;
	}


	/**
	 * @param doc the doc to set
	 */
	public void setDoc(String doc) {
		this.doc = doc;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result + ((doc == null) ? 0 : doc.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActivityLikeEntity other = (ActivityLikeEntity) obj;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (doc == null) {
			if (other.doc != null)
				return false;
		} else if (!doc.equals(other.doc))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}
    
    
}
