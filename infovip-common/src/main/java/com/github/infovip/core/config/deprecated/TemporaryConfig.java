package com.github.infovip.core.config.deprecated;

import java.util.ArrayList;
import java.util.List;


public class TemporaryConfig {
	
	private Smtp smtp;

	private String websiteAddress;
	
	private List<String> staticSiteAddress;
	
	private Facebook facebook;
	
	private GoogleAuth google;
	
	private BarionConfiguration barion;
	
	private NetworkFileSystem nfs;
	
	public TemporaryConfig() {
		this.staticSiteAddress = new ArrayList<>();
	}

	public Smtp getSmtp() {
		return smtp;
	}

	public void setSmtp(Smtp smtp) {
		this.smtp = smtp;
	}
	

	public String getWebsiteAddress() {
		return websiteAddress;
	}

	public void setWebsiteAddress(String websiteAddress) {
		this.websiteAddress = websiteAddress;
	}
	
	public Facebook getFacebook() {
		return facebook;
	}

	public void setFacebook(Facebook facebook) {
		this.facebook = facebook;
	}

	
	public void setGoogle(GoogleAuth google) {
		this.google = google;
	}
	
	public GoogleAuth getGoogle() {
		return google;
	}
	
	public BarionConfiguration getBarion() {
		return barion;
	}
	
	public void setBarion(BarionConfiguration barion) {
		this.barion = barion;
	}
	
	
	/**
	 * @return the staticSiteAddress
	 */
	public List<String> getStaticSiteAddress() {
		return staticSiteAddress;
	}

	/**
	 * @param staticSiteAddress the staticSiteAddress to set
	 */
	public void setStaticSiteAddress(List<String> staticSiteAddress) {
		this.staticSiteAddress = staticSiteAddress;
	}

	/**
	 * @return the nfs
	 */
	public NetworkFileSystem getNfs() {
		return nfs;
	}

	/**
	 * @param nfs the nfs to set
	 */
	public void setNfs(NetworkFileSystem nfs) {
		this.nfs = nfs;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barion == null) ? 0 : barion.hashCode());
		result = prime * result + ((facebook == null) ? 0 : facebook.hashCode());
		result = prime * result + ((google == null) ? 0 : google.hashCode());
		result = prime * result + ((nfs == null) ? 0 : nfs.hashCode());
		result = prime * result + ((smtp == null) ? 0 : smtp.hashCode());
		result = prime * result + ((staticSiteAddress == null) ? 0 : staticSiteAddress.hashCode());
		result = prime * result + ((websiteAddress == null) ? 0 : websiteAddress.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TemporaryConfig other = (TemporaryConfig) obj;
		if (barion == null) {
			if (other.barion != null)
				return false;
		} else if (!barion.equals(other.barion))
			return false;
		if (facebook == null) {
			if (other.facebook != null)
				return false;
		} else if (!facebook.equals(other.facebook))
			return false;
		if (google == null) {
			if (other.google != null)
				return false;
		} else if (!google.equals(other.google))
			return false;
		if (nfs == null) {
			if (other.nfs != null)
				return false;
		} else if (!nfs.equals(other.nfs))
			return false;
		if (smtp == null) {
			if (other.smtp != null)
				return false;
		} else if (!smtp.equals(other.smtp))
			return false;
		if (staticSiteAddress == null) {
			if (other.staticSiteAddress != null)
				return false;
		} else if (!staticSiteAddress.equals(other.staticSiteAddress))
			return false;
		if (websiteAddress == null) {
			if (other.websiteAddress != null)
				return false;
		} else if (!websiteAddress.equals(other.websiteAddress))
			return false;
		return true;
	}
	
	
}
