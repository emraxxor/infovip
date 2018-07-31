package com.github.infovip.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the cfg_source database table.
 * 
 */
@Entity
@Table(name="cfg_source")
@NamedQuery(name="CfgSource.findAll", query="SELECT c FROM CfgSource c")
public class CfgSource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="source_id")
	private Long sourceId;

	@Column(name="company_id")
	private Long companyId;
	
	@Column(name="webshop_id")
	private Long webshopId;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="is_visible")
	private Boolean isVisible;

	@Column(name="source_name")
	private String sourceName;

	@Column(name="source_url")
	private String sourceUrl;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="cfgSource")
	@JoinColumn(name="source_id")
	private CfgSourceDataMarker marker;
	
	
	public CfgSource() {
	}

	public Long getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsVisible() {
		return this.isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}

	public String getSourceName() {
		return this.sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceUrl() {
		return this.sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	
	public Long getWebshopId() {
		return webshopId;
	}
	
	public void setWebshopId(Long webshopId) {
		this.webshopId = webshopId;
	}
	
	public CfgSourceDataMarker getMarker() {
		return marker;
	}
	
	public void setMarker(CfgSourceDataMarker marker) {
		this.marker = marker;
	}

}