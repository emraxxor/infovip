package com.github.infovip.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the user_blog database table.
 * 
 */
@Entity
@Table(name="user_blog")
@NamedQuery(name="UserBlog.findAll", query="SELECT u FROM UserBlog u")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserBlog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long bid;

	private String bname;
	
	@Column(name="creation_time")
	private LocalDateTime creationTime;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public UserBlog(User u, String name) {
		this.user = u;
		this.creationTime = LocalDateTime.now();
		this.bname = name;
	}

	
    @PrePersist
    public void prePersist() {
    	if ( this.user == null )
    		throw new IllegalStateException();
    }
 
    @PreUpdate
    public void preUpdate() {
		this.creationTime = LocalDateTime.now();
    }


}