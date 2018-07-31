package com.github.infovip.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the cfg_source_data_marker database table.
 * 
 */
@Entity
@Table(name="cfg_source_data_marker")
@NamedQuery(name="CfgSourceDataMarker.findAll", query="SELECT c FROM CfgSourceDataMarker c")
public class CfgSourceDataMarker implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="src_marker_id")
	private Long srcMarkerId;

	@Column(name="cfg_marker_data")
	private byte[] cfgMarkerData;
	
	//bi-directional many-to-one association to CfgSource
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cfg_src_id", referencedColumnName = "source_id")
	private CfgSource cfgSource;
	
	public CfgSourceDataMarker() {
	}

	public Long getSrcMarkerId() {
		return this.srcMarkerId;
	}

	public void setSrcMarkerId(Long srcMarkerId) {
		this.srcMarkerId = srcMarkerId;
	}

	public byte[] getCfgMarkerData() {
		return this.cfgMarkerData;
	}

	public void setCfgMarkerData(byte[] cfgMarkerData) {
		this.cfgMarkerData = cfgMarkerData;
	}

	public CfgSource getCfgSource() {
		return this.cfgSource;
	}

	public void setCfgSource(CfgSource cfgSource) {
		this.cfgSource = cfgSource;
	}
	

}