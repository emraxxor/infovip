package com.github.infovip.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.github.infovip.core.date.DefaultDateFormatter;


/**
 * The persistent class for the user_blog database table.
 * 
 */
@Entity
@Table(name="user_blog")
@NamedQuery(name="UserBlog.findAll", query="SELECT u FROM UserBlog u")
public class UserBlog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long bid;

	private String bname;
	
	@Transient
	private Long userId;

	@Column(name="\"creationTime\"")
	private Timestamp creationTime;

	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="userid",referencedColumnName="uid", updatable = false, insertable = true)
	private User user;
	
	public UserBlog() {
	}
	
	public UserBlog(User u, String name) {
		this.user = u;
		this.creationTime = DefaultDateFormatter.timestamp();
		this.bname = name;
	}

	
    @PrePersist
    public void prePersist() {
    	if ( this.user == null )
    		throw new IllegalStateException();
    }
 
    @PreUpdate
    public void preUpdate() {
		this.creationTime = DefaultDateFormatter.timestamp();
    }

    
	public Long getBid() {
		return this.bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	public String getBname() {
		return this.bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public Timestamp getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}

}