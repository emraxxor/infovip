package com.github.infovip.entities;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the cfg_application_meta_data database table.
 * 
 */
@Entity
@Table(name="cfg_application_meta_data")
@NamedQuery(name="CfgApplicationMetaData.findAll", query="SELECT c FROM CfgApplicationMetaData c")
@Getter
@Setter
@NoArgsConstructor
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

}