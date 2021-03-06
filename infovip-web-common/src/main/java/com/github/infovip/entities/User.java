/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.github.infovip.core.form.data.UserIface;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author attila
 */
@Entity
@Table(name = "cfg_users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUid", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByUname", query = "SELECT u FROM User u WHERE u.userName = :userName"),
    @NamedQuery(name = "User.findByUpassword", query = "SELECT u FROM User u WHERE u.userPassword = :userPassword"),
    @NamedQuery(name = "User.findByNameAndUpassword", query = "SELECT u FROM User u WHERE u.userPassword = :userPassword and u.userName = :userName"),
    @NamedQuery(name = "User.findByUmail", query = "SELECT u FROM User u WHERE u.userMail = :userMail")})
@Data
@NoArgsConstructor
public class User implements Serializable, UserIface<LogRegistration> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "uid")
	private Long userId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "uname")
    private String userName;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "upassword")
    private String userPassword;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "umail")
    private String userMail;
    
    @Column(name="upicture")
    @Size(min=1, max=200)
    private String picture;
    
    @Column(name="is_active")
    @NotNull
    private Boolean isActive;
    
    @Column(name="firstname")
    private String firstName;
    
    @Column(name="lastname")
    private String lastName;

    @Column(name="city")
    private String city;
    
    @Column(name="country")
    private String country;
    
    @Column(name="county")
    private String county;
    
    @Column(name="last_seen")
    private LocalDateTime lastSeen;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "uid", fetch = FetchType.LAZY)
    private LogRegistration logRegistration;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<UserBlog> blogs;

    @XmlTransient
    public LogRegistration getLogRegistration() {
        return logRegistration;
    }

}
