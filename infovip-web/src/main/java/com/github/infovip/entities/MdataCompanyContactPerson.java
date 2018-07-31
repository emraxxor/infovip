package com.github.infovip.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the cfg_source_data_marker database table.
 * 
 */
@Entity
@Table(name="mdata_company_contact_person")
@NamedQuery(name="MdataCompanyContactPerson.findAll", query="SELECT c FROM MdataCompanyContactPerson c")
public class MdataCompanyContactPerson implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long contactPersonId;

	@Column(name="contact_person_name")
	private String contactPersonName;

	@Column(name="contact_person_phone_number")
	private String contactPersonPhoneNumber;

	@Column(name="contact_person_email")
	private String contactPersonEmail;

	public MdataCompanyContactPerson() {
	}

	public Long getContactPersonId() {
		return contactPersonId;
	}

	public void setContactPersonId(Long contactPersonId) {
		this.contactPersonId = contactPersonId;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getContactPersonPhoneNumber() {
		return contactPersonPhoneNumber;
	}

	public void setContactPersonPhoneNumber(String contactPersonPhoneNumber) {
		this.contactPersonPhoneNumber = contactPersonPhoneNumber;
	}

	public String getContactPersonEmail() {
		return contactPersonEmail;
	}

	public void setContactPersonEmail(String contactPersonEmail) {
		this.contactPersonEmail = contactPersonEmail;
	}
}