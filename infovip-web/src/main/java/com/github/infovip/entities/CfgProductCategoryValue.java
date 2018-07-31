package com.github.infovip.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.github.infovip.entities.listener.CfgProductCategoryValueListener;

import static javax.persistence.CascadeType.ALL;


/**
 * The persistent class for the cfg_product_category_value database table.
 * 
 */
@Entity
@Table(name="cfg_product_category_value")
@EntityListeners({CfgProductCategoryValueListener.class})
@NamedQuery(name="CfgProductCategoryValue.findAll", query="SELECT c FROM CfgProductCategoryValue c")
public class CfgProductCategoryValue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cfg_prod_value_id")
	private Long cfgProdValueId;

	@Column(name="utype")
	private String utype;
	
	//bi-directional many-to-one association to CfgProductCategory
	@ManyToOne(cascade=ALL)
	@JoinColumn(name="cfg_product_category_id")
	private CfgProductCategory cfgProductCategory;

	@OneToOne
	@JoinColumn(name = "cfg_param_value_id", referencedColumnName = "parameter_value_id", insertable = false, updatable = false )
	private CfgParameterValue cfgParameterValue;

	@OneToOne
	@JoinColumn(name="cfg_param_id", referencedColumnName="parameter_id", insertable=false, updatable=false)
	private CfgParameter cfgParameter;
	
	@Column(name = "cfg_param_id")
	private Long cfgParamId;
	
	@Column(name = "cfg_param_value_id")
	private Long cfgParamValueId;
	
	@Column(name="pos")
	private Long pos;


	
	public CfgProductCategoryValue() {
	}

	public CfgProductCategoryValue(Long cfgProdValueId, String utype, Long cfgParamId, Long cfgParamValueId,Long pos) {
		super();
		this.cfgProdValueId = cfgProdValueId;
		this.utype = utype;
		this.cfgParamId = cfgParamId;
		this.cfgParamValueId = cfgParamValueId;
		this.pos = pos;
	}


	public Long getCfgProdValueId() {
		return this.cfgProdValueId;
	}

	public void setCfgProdValueId(Long cfgProdValueId) {
		this.cfgProdValueId = cfgProdValueId;
	}

	
	public void setCfgParameterValue(CfgParameterValue cfgParameterValue) {
		this.cfgParameterValue = cfgParameterValue;
	}
	
	public CfgParameterValue getCfgParameterValue() {
		return cfgParameterValue;
	}

	public CfgProductCategory getCfgProductCategory() {
		return this.cfgProductCategory;
	}
	
	public CfgParameter getCfgParameter() {
		return cfgParameter;
	}
	
	public void setCfgParameter(CfgParameter cfgParameter) {
		this.cfgParameter = cfgParameter;
	}

	public void setCfgProductCategory(CfgProductCategory cfgProductCategory) {
		this.cfgProductCategory = cfgProductCategory;
	}
	
	public String getUtype() {
		return utype;
	}
	
	public void setUtype(String utype) {
		this.utype = utype;
	}
	
	public Long getCfgParamId() {
		return cfgParamId;
	}

	public void setCfgParamId(Long cfgParamId) {
		this.cfgParamId = cfgParamId;
	}

	public Long getCfgParamValueId() {
		return cfgParamValueId;
	}

	public void setCfgParamValueId(Long cfgParamValueId) {
		this.cfgParamValueId = cfgParamValueId;
	}
	
	public Long getPos() {
		return pos;
	}
	
	public void setPos(Long pos) {
		this.pos = pos;
	}

}