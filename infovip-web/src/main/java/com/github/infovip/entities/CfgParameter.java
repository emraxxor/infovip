package com.github.infovip.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import static javax.persistence.FetchType.EAGER;


/**
 * The persistent class for the cfg_parameter database table.
 * 
 */
@Entity
@Table(name="cfg_parameter")
@NamedQuery(name="CfgParameter.findAll", query="SELECT c FROM CfgParameter c")
public class CfgParameter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="parameter_id")
	private Long parameterId;

	private Boolean open;

	@Column(name="parameter_description")
	private String parameterDescription;

	@Column(name="parameter_name")
	private String parameterName;

	@Column(name="\"paramValue_readonly\"")
	private Boolean paramValue_readonly;

	@Column(name="param_display_name")
	private String paramDisplayName;
	
	@Column(name="param_available")
	private Boolean paramAvailable;
	
	private String utype;
	
	@Column(name="position")
	private Long position;
	
	//bi-directional many-to-one association to CfgParameterValue
	@OneToMany(mappedBy="cfgParameter",cascade=CascadeType.ALL, fetch = EAGER, orphanRemoval=true, targetEntity=CfgParameterValue.class)
	@OrderBy("position ASC")
	private List<CfgParameterValue> cfgParameterValues;

	public CfgParameter() {
	}
	
	

	public CfgParameter(Long parameterId, Boolean open, String parameterDescription, String parameterName,
			Boolean paramValue_readonly, String utype, List<CfgParameterValue> cfgParameterValues, Long position,
			String paramDisplayName, Boolean paramAvailable
			) {
		super();
		this.parameterId = parameterId;
		this.open = open;
		this.parameterDescription = parameterDescription;
		this.parameterName = parameterName;
		this.paramValue_readonly = paramValue_readonly;
		this.utype = utype;
		this.cfgParameterValues = new ArrayList<>();
		this.position = position;
		this.paramAvailable = paramAvailable;
		this.paramDisplayName = paramDisplayName;
		
		for(CfgParameterValue val : cfgParameterValues ) {
			val.setCfgParameter(this);
			addCfgParameterValue(val);
		}
	}



	public Long getParameterId() {
		return this.parameterId;
	}

	public void setParameterId(Long parameterId) {
		this.parameterId = parameterId;
	}

	public Boolean getOpen() {
		return this.open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public String getParameterDescription() {
		return this.parameterDescription;
	}

	public void setParameterDescription(String parameterDescription) {
		this.parameterDescription = parameterDescription;
	}

	public String getParameterName() {
		return this.parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public Boolean getParamValue_readonly() {
		return this.paramValue_readonly;
	}

	public void setParamValue_readonly(Boolean paramValue_readonly) {
		this.paramValue_readonly = paramValue_readonly;
	}

	public String getUtype() {
		return this.utype;
	}

	public void setUtype(String utype) {
		this.utype = utype;
	}

	public List<CfgParameterValue> getCfgParameterValues() {
		return this.cfgParameterValues;
	}
	
	public Long getPosition() {
		return position;
	}
	
	public void setPosition(Long position) {
		this.position = position;
	}
	
	public Boolean getParamAvailable() {
		return paramAvailable;
	}
	
	public String getParamDisplayName() {
		return paramDisplayName;
	}
	
	public void setParamDisplayName(String paramDisplayName) {
		this.paramDisplayName = paramDisplayName;
	}
	
	public void setParamAvailable(Boolean paramAvailable) {
		this.paramAvailable = paramAvailable;
	}

	public void setCfgParameterValues(List<CfgParameterValue> cfgParameterValues) {
		this.cfgParameterValues = cfgParameterValues;
	}

	public CfgParameterValue addCfgParameterValue(CfgParameterValue cfgParameterValue) {
		getCfgParameterValues().add(cfgParameterValue);
		cfgParameterValue.setCfgParameter(this);
		return cfgParameterValue;
	}
	

	public CfgParameterValue removeCfgParameterValue(CfgParameterValue cfgParameterValue) {
		getCfgParameterValues().remove(cfgParameterValue);
		cfgParameterValue.setCfgParameter(null);
		return cfgParameterValue;
	}

}