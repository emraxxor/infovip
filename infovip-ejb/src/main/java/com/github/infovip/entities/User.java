/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author attila
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUid", query = "SELECT u FROM User u WHERE u.uid = :uid"),
    @NamedQuery(name = "User.findByUname", query = "SELECT u FROM User u WHERE u.uname = :uname"),
    @NamedQuery(name = "User.findByUpassword", query = "SELECT u FROM User u WHERE u.upassword = :upassword"),
    @NamedQuery(name = "User.findByNameAndUpassword", query = "SELECT u FROM User u WHERE u.upassword = :upassword and u.uname = :uname"),
    @NamedQuery(name = "User.findByUmail", query = "SELECT u FROM User u WHERE u.umail = :umail")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "uid")
    private Long uid;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "uname")
    private String uname;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "upassword")
    private String upassword;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "umail")
    private String umail;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "uid")
    private LogRegistration logRegistration;

    public User() {
    }

    public User(Long uid) {
        this.uid = uid;
    }

    public User(Long uid, String uname, String upassword, String umail) {
        this.uid = uid;
        this.uname = uname;
        this.upassword = upassword;
        this.umail = umail;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUmail() {
        return umail;
    }

    public void setUmail(String umail) {
        this.umail = umail;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.uid);
        hash = 11 * hash + Objects.hashCode(this.uname);
        hash = 11 * hash + Objects.hashCode(this.upassword);
        hash = 11 * hash + Objects.hashCode(this.umail);
        hash = 11 * hash + Objects.hashCode(this.logRegistration);
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
        final User other = (User) obj;
        if (!Objects.equals(this.uname, other.uname)) {
            return false;
        }
        if (!Objects.equals(this.upassword, other.upassword)) {
            return false;
        }
        if (!Objects.equals(this.umail, other.umail)) {
            return false;
        }
        if (!Objects.equals(this.uid, other.uid)) {
            return false;
        }
        if (!Objects.equals(this.logRegistration, other.logRegistration)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.github.infovip.Users[ uid=" + uid + " ]";
    }

    @XmlTransient
    public LogRegistration getLogRegistration() {
        return logRegistration;
    }

    public void setLogRegistration(LogRegistration logRegistration) {
        this.logRegistration = logRegistration;
    }

}
