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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.github.infovip.core.form.data.LogIFace;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author attila
 */
@Entity
@Table(name = "cfg_reg_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogRegistration.findAll", query = "SELECT l FROM LogRegistration l"),
    @NamedQuery(name = "LogRegistration.findByPid", query = "SELECT l FROM LogRegistration l WHERE l.pid = :pid"),
    @NamedQuery(name = "LogRegistration.findByIp", query = "SELECT l FROM LogRegistration l WHERE l.ip = :ip"),
    @NamedQuery(name = "LogRegistration.findByTime", query = "SELECT l FROM LogRegistration l WHERE l.creationTime = :time")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogRegistration implements LogIFace<User> , Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    private Date creationTime;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "uid", referencedColumnName = "uid")
	private User uid;

}
