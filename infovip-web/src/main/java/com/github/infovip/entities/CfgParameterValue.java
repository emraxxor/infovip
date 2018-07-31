package com.github.infovip.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cfg_parameter_value database table.
 * 
 */
@Entity
@Table(name="cfg_parameter_value")
@NamedQuery(name="CfgParameterValue.findAll", query="SELECT c FROM CfgParameterValue c")
public class CfgParameterValue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="parameter_value_id")
	private Long parameterValueId;

	@Column(name="\"paramDescription_readonly\"")
	private Boolean paramDescription_readonly;

	@Column(name="parameter_value")
	private String parameterValue;

	@Column(name="\"paramName_readonly\"")
	private Boolean paramName_readonly;

	private String utype;
	
	@Column(name="position")
	private Long position;


	//bi-directional many-to-one association to CfgParameter
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="parameter_id")
	private CfgParameter cfgParameter;

	public CfgParameterValue() {
	}

	public CfgParameterValue(Long parameterValueId, Boolean paramDescription_readonly, String parameterValue,
			Boolean paramName_readonly, String utype, Long position) {
		super();
		this.parameterValueId = parameterValueId;
		this.paramDescription_readonly = paramDescription_readonly;
		this.parameterValue = parameterValue;
		this.paramName_readonly = paramName_readonly;
		this.utype = utype;
		this.position = position;
	}



	public Long getParameterValueId() {
		return this.parameterValueId;
	}

	public void setParameterValueId(Long parameterValueId) {
		this.parameterValueId = parameterValueId;
	}

	public Boolean getParamDescription_readonly() {
		return this.paramDescription_readonly;
	}

	public void setParamDescription_readonly(Boolean paramDescription_readonly) {
		this.paramDescription_readonly = paramDescription_readonly;
	}

	public String getParameterValue() {
		return this.parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	public Boolean getParamName_readonly() {
		return this.paramName_readonly;
	}

	public void setParamName_readonly(Boolean paramName_readonly) {
		this.paramName_readonly = paramName_readonly;
	}

	public String getUtype() {
		return this.utype;
	}

	public void setUtype(String utype) {
		this.utype = utype;
	}

	public CfgParameter getCfgParameter() {
		return this.cfgParameter;
	}

	public void setCfgParameter(CfgParameter cfgParameter) {
		this.cfgParameter = cfgParameter;
	}
	
	public Long getPosition() {
		return position;
	}
	
	public void setPosition(Long position) {
		this.position = position;
	}

}