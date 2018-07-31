package com.github.infovip.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.google.gson.GsonBuilder;


/**
 * The persistent class for the cfg_source database table.
 * 
 */
@Entity
@Table(name="mdata_company")
@NamedQuery(name="MdataCompany.findAll", query="SELECT c FROM MdataCompany c")
public class MdataCompany implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long companyid;

	@Column(name="status")
	private Integer isActive;

	@Column(name="company_name")
	private String companyName;

	@Column(name="tax_id")
	private String taxId;

	@Column(name="based_zip")
	private String basedZip;

	@Column(name="based_country")
	private String basedCountry;

	@Column(name="based_state")
	private String basedState;

	@Column(name="based_city")
	private String basedCity;

	@Column(name="based_address")
	private String basedAddress;

	@Column(name="comment")
	private String comment;

	@Column(name="registration_number")
	private String registrationNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name="creation_time",insertable = true, nullable = false, updatable = false)
	private Date creationTime;

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name="modification_time", nullable = false)
	private Date modificationTime;

	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="company_id", referencedColumnName="id")
	private Set<MdataCompanyBillingAddress> mdataCompanyBillingAddress;

	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="company_id", referencedColumnName="id")
	private Set<MdataCompanyContactPerson> mdataCompanyContactPerson;

	public MdataCompany() {
	}

	public Long getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getBasedZip() {
		return basedZip;
	}

	public void setBasedZip(String basedZip) {
		this.basedZip = basedZip;
	}

	public String getBasedCountry() {
		return basedCountry;
	}

	public void setBasedCountry(String basedCountry) {
		this.basedCountry = basedCountry;
	}

	public String getBasedState() {
		return basedState;
	}

	public void setBasedState(String basedState) {
		this.basedState = basedState;
	}

	public String getBasedCity() {
		return basedCity;
	}

	public void setBasedCity(String basedCity) {
		this.basedCity = basedCity;
	}

	public String getBasedAddress() {
		return basedAddress;
	}

	public void setBasedAddress(String basedAddress) {
		this.basedAddress = basedAddress;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}


	public Set<MdataCompanyBillingAddress> getMdataCompanyBillingAddress() {
		return mdataCompanyBillingAddress;
	}


	public void setMdataCompanyBillingAddress(Set<MdataCompanyBillingAddress> mdataCompanyBillingAddress) {
		this.mdataCompanyBillingAddress = mdataCompanyBillingAddress;
	}


	public Set<MdataCompanyContactPerson> getMdataCompanyContactPerson() {
		return mdataCompanyContactPerson;
	}


	public void setMdataCompanyContactPerson(Set<MdataCompanyContactPerson> mdataCompanyContactPerson) {
		this.mdataCompanyContactPerson = mdataCompanyContactPerson;
	}


	public String toJson() {
		return new GsonBuilder().serializeNulls().create().toJson(this);
	}
}