package com.github.infovip.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cfg_test database table.
 * 
 */
@Entity
@Table(name="cfg_test")
@NamedQuery(name="CfgTest.findAll", query="SELECT c FROM CfgTest c")
public class CfgTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pid;

	private String ccol1;

	private String ccol2;

	private String csearch;

	public CfgTest() {
	}

	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getCcol1() {
		return this.ccol1;
	}

	public void setCcol1(String ccol1) {
		this.ccol1 = ccol1;
	}

	public String getCcol2() {
		return this.ccol2;
	}

	public void setCcol2(String ccol2) {
		this.ccol2 = ccol2;
	}

	public String getCsearch() {
		return this.csearch;
	}

	public void setCsearch(String csearch) {
		this.csearch = csearch;
	}

}