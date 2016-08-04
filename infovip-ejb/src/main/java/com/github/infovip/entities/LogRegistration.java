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
package com.github.infovip.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author attila
 */
@Entity
@Table(name = "log_registration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogRegistration.findAll", query = "SELECT l FROM LogRegistration l"),
    @NamedQuery(name = "LogRegistration.findByPid", query = "SELECT l FROM LogRegistration l WHERE l.pid = :pid"),
    @NamedQuery(name = "LogRegistration.findByIp", query = "SELECT l FROM LogRegistration l WHERE l.ip = :ip"),
    @NamedQuery(name = "LogRegistration.findByTime", query = "SELECT l FROM LogRegistration l WHERE l.time = :time")})
public class LogRegistration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pid")
    private Long pid;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 39)
    @Column(name = "ip")
    private String ip;

    @Basic(optional = true)
    @NotNull
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    private User uid;

    public LogRegistration() {
    }

    public LogRegistration(Long pid) {
        this.pid = pid;
    }

    public LogRegistration(Long pid, String ip, Date time) {
        this.pid = pid;
        this.ip = ip;
        this.time = time;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getUid() {
        return uid;
    }

    public void setUid(User uid) {
        this.uid = uid;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(pid).append(ip).append(time).append(uid).toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogRegistration)) {
            return false;
        }
        LogRegistration other = (LogRegistration) object;
        return new EqualsBuilder().append(this.pid, other.pid).append(this.ip, other.ip).append(this.uid, other.uid).append(this.time, other.time).isEquals();
    }

    @Override
    public String toString() {
        return "com.github.infovip.entities.LogRegistration[ pid=" + pid + " ]";
    }

}
