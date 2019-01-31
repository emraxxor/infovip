package com.github.infovip.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cfg_application_meta_data database table.
 * 
 */
@Entity
@Table(name="cfg_application_meta_data")
@NamedQuery(name="CfgApplicationMetaData.findAll", query="SELECT c FROM CfgApplicationMetaData c")
public class CfgApplicationMetaData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="meta_description")
	private String metaDescription;

	@Column(name="meta_keyword")
	private String metaKeyword;

	@Column(name="meta_url")
	private String metaUrl;

	@Column(name="og_description")
	private String ogDescription;

	@Column(name="og_title")
	private String ogTitle;

	@Column(name="og_type")
	private String ogType;

	private String thumbnail;

	@Column(name="thumbnail_link")
	private String thumbnailLink;

	private String title;

	public CfgApplicationMetaData() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getMetaDescription() {
		return this.metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getMetaKeyword() {
		return this.metaKeyword;
	}

	public void setMetaKeyword(String metaKeyword) {
		this.metaKeyword = metaKeyword;
	}

	public String getMetaUrl() {
		return this.metaUrl;
	}

	public void setMetaUrl(String metaUrl) {
		this.metaUrl = metaUrl;
	}

	public String getOgDescription() {
		return this.ogDescription;
	}

	public void setOgDescription(String ogDescription) {
		this.ogDescription = ogDescription;
	}

	public String getOgTitle() {
		return this.ogTitle;
	}

	public void setOgTitle(String ogTitle) {
		this.ogTitle = ogTitle;
	}

	public String getOgType() {
		return this.ogType;
	}

	public void setOgType(String ogType) {
		this.ogType = ogType;
	}

	public String getThumbnail() {
		return this.thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getThumbnailLink() {
		return this.thumbnailLink;
	}

	public void setThumbnailLink(String thumbnailLink) {
		this.thumbnailLink = thumbnailLink;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}