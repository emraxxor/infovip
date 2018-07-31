package com.github.infovip.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the cfg_product_category database table.
 * 
 */
@Entity
@Table(name="cfg_product_category")
@NamedQuery(name="CfgProductCategory.findAll", query="SELECT c FROM CfgProductCategory c")
public class CfgProductCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_category_id")
	private Long productCategoryId;

	private Boolean open;

	@Column(name="\"paramValue_readonly\"")
	private Boolean paramValue_readonly;

	@Column(name="product_category_name")
	private String productCategoryName;

	//bi-directional many-to-one association to CfgProductCategoryValue
	@OneToMany(mappedBy="cfgProductCategory", fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval = true)
	@OrderBy("pos ASC")
	private List<CfgProductCategoryValue> cfgProductCategoryValues;

	@Column(name="pos")
	private Long pos;
	
	@Column(name="utype")
	private String utype;
	
	public CfgProductCategory() {
	}

	
	
	public CfgProductCategory(Long productCategoryId, Boolean open, Boolean paramValue_readonly,
			String productCategoryName, List<CfgProductCategoryValue> cfgProductCategoryValues, Long pos,
			String utype) {
		super();
		this.productCategoryId = productCategoryId;
		this.open = open;
		this.paramValue_readonly = paramValue_readonly;
		this.productCategoryName = productCategoryName;
		this.pos = pos;
		this.cfgProductCategoryValues = new ArrayList<>();
		this.utype = utype;
		
		for(CfgProductCategoryValue val : cfgProductCategoryValues ) {
			addCfgProductCategoryValue(val);
		}

	}

	public Long getProductCategoryId() {
		return this.productCategoryId;
	}

	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public Boolean getOpen() {
		return this.open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getParamValue_readonly() {
		return this.paramValue_readonly;
	}

	public void setParamValue_readonly(Boolean paramValue_readonly) {
		this.paramValue_readonly = paramValue_readonly;
	}

	public String getProductCategoryName() {
		return this.productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public List<CfgProductCategoryValue> getCfgProductCategoryValues() {
		return this.cfgProductCategoryValues;
	}

	public void setCfgProductCategoryValues(List<CfgProductCategoryValue> cfgProductCategoryValues) {
		this.cfgProductCategoryValues = cfgProductCategoryValues;
	}
	
	public Long getPos() {
		return pos;
	}
	
	public void setPos(Long pos) {
		this.pos = pos;
	}
	
	public String getUtype() {
		return utype;
	}
	
	public void setUtype(String utype) {
		this.utype = utype;
	}

	public CfgProductCategoryValue addCfgProductCategoryValue(CfgProductCategoryValue cfgProductCategoryValue) {
		getCfgProductCategoryValues().add(cfgProductCategoryValue);
		cfgProductCategoryValue.setCfgProductCategory(this);

		return cfgProductCategoryValue;
	}

	public CfgProductCategoryValue removeCfgProductCategoryValue(CfgProductCategoryValue cfgProductCategoryValue) {
		getCfgProductCategoryValues().remove(cfgProductCategoryValue);
		cfgProductCategoryValue.setCfgProductCategory(null);

		return cfgProductCategoryValue;
	}

}