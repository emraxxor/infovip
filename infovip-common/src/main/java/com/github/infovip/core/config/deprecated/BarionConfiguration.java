package com.github.infovip.core.config.deprecated;

public class BarionConfiguration {

	private String baseuri;
	
	private String poskey;
	
	private String payee;
	
	private String module;

	private String applicationURL;
	
	private String contentType;
	
	private String host;
	
	private String userAgent;
	
	private String httpAccept;

	private String paymentStateURL;
	
	public String getBaseuri() {
		return baseuri;
	}

	public void setBaseuri(String baseuri) {
		this.baseuri = baseuri;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getApplicationURL() {
		return applicationURL;
	}
	
	public void setApplicationURL(String applicationURL) {
		this.applicationURL = applicationURL;
	}

	public String getPoskey() {
		return poskey;
	}

	public void setPoskey(String poskey) {
		this.poskey = poskey;
	}
	
	

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getHttpAccept() {
		return httpAccept;
	}

	public void setHttpAccept(String httpAccept) {
		this.httpAccept = httpAccept;
	}

	
	
	/**
	 * @return the paymentStateURL
	 */
	public String getPaymentStateURL() {
		return paymentStateURL;
	}

	/**
	 * @param paymentStateURL the paymentStateURL to set
	 */
	public void setPaymentStateURL(String paymentStateURL) {
		this.paymentStateURL = paymentStateURL;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicationURL == null) ? 0 : applicationURL.hashCode());
		result = prime * result + ((baseuri == null) ? 0 : baseuri.hashCode());
		result = prime * result + ((contentType == null) ? 0 : contentType.hashCode());
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + ((httpAccept == null) ? 0 : httpAccept.hashCode());
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		result = prime * result + ((payee == null) ? 0 : payee.hashCode());
		result = prime * result + ((paymentStateURL == null) ? 0 : paymentStateURL.hashCode());
		result = prime * result + ((poskey == null) ? 0 : poskey.hashCode());
		result = prime * result + ((userAgent == null) ? 0 : userAgent.hashCode());
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
		BarionConfiguration other = (BarionConfiguration) obj;
		if (applicationURL == null) {
			if (other.applicationURL != null)
				return false;
		} else if (!applicationURL.equals(other.applicationURL))
			return false;
		if (baseuri == null) {
			if (other.baseuri != null)
				return false;
		} else if (!baseuri.equals(other.baseuri))
			return false;
		if (contentType == null) {
			if (other.contentType != null)
				return false;
		} else if (!contentType.equals(other.contentType))
			return false;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (httpAccept == null) {
			if (other.httpAccept != null)
				return false;
		} else if (!httpAccept.equals(other.httpAccept))
			return false;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		if (payee == null) {
			if (other.payee != null)
				return false;
		} else if (!payee.equals(other.payee))
			return false;
		if (paymentStateURL == null) {
			if (other.paymentStateURL != null)
				return false;
		} else if (!paymentStateURL.equals(other.paymentStateURL))
			return false;
		if (poskey == null) {
			if (other.poskey != null)
				return false;
		} else if (!poskey.equals(other.poskey))
			return false;
		if (userAgent == null) {
			if (other.userAgent != null)
				return false;
		} else if (!userAgent.equals(other.userAgent))
			return false;
		return true;
	}

	
	
}
