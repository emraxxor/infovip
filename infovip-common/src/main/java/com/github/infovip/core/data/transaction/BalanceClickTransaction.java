package com.github.infovip.core.data.transaction;

import com.github.infovip.core.data.TransactionDataElement;
import com.github.infovip.core.data.type.TransactionType;
import com.github.infovip.core.date.DefaultDateFormatter;
import com.github.infovip.core.es.type.Currency;
import com.github.infovip.core.es.type.DefaultDocumentType;

public class BalanceClickTransaction extends TransactionDataElement {

	private Long webshopId;
	
	private Long sourceId;
	
	private String remoteIP;
	
	private String remoteHost;
	
	private String browser;
	
	public BalanceClickTransaction(
			Long companyId, 
			TransactionType type, 
			String creationTime, 
			Double currentBalance,
			Double amount,
			Currency currency
		) {
		super(companyId, type, creationTime, currentBalance, amount,currency);
	}
	
	
	public BalanceClickTransaction(
			DefaultDocumentType ddt, 
			TransactionType type, 
			Double amount, 
			Double currentBalance,
			String remoteIp,
			String remoteHost,
			String browser,
			Currency currency
			) {
		this(ddt.getCompanyId(), type , DefaultDateFormatter.current(), currentBalance, amount,currency);
		this.remoteIP = remoteIp;
		this.remoteHost = remoteHost;
		this.browser = browser;
	}

	
	
	/**
	 * @return the webshopId
	 */
	public Long getWebshopId() {
		return webshopId;
	}

	/**
	 * @param webshopId the webshopId to set
	 */
	public void setWebshopId(Long webshopId) {
		this.webshopId = webshopId;
	}

	/**
	 * @return the sourceId
	 */
	public Long getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId the sourceId to set
	 */
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @return the remoteIP
	 */
	public String getRemoteIP() {
		return remoteIP;
	}

	/**
	 * @param remoteIP the remoteIP to set
	 */
	public void setRemoteIP(String remoteIP) {
		this.remoteIP = remoteIP;
	}

	/**
	 * @return the remoteHost
	 */
	public String getRemoteHost() {
		return remoteHost;
	}

	/**
	 * @param remoteHost the remoteHost to set
	 */
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	/**
	 * @return the browser
	 */
	public String getBrowser() {
		return browser;
	}

	/**
	 * @param browser the browser to set
	 */
	public void setBrowser(String browser) {
		this.browser = browser;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((browser == null) ? 0 : browser.hashCode());
		result = prime * result + ((remoteHost == null) ? 0 : remoteHost.hashCode());
		result = prime * result + ((remoteIP == null) ? 0 : remoteIP.hashCode());
		result = prime * result + ((sourceId == null) ? 0 : sourceId.hashCode());
		result = prime * result + ((webshopId == null) ? 0 : webshopId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BalanceClickTransaction other = (BalanceClickTransaction) obj;
		if (browser == null) {
			if (other.browser != null)
				return false;
		} else if (!browser.equals(other.browser))
			return false;
		if (remoteHost == null) {
			if (other.remoteHost != null)
				return false;
		} else if (!remoteHost.equals(other.remoteHost))
			return false;
		if (remoteIP == null) {
			if (other.remoteIP != null)
				return false;
		} else if (!remoteIP.equals(other.remoteIP))
			return false;
		if (sourceId == null) {
			if (other.sourceId != null)
				return false;
		} else if (!sourceId.equals(other.sourceId))
			return false;
		if (webshopId == null) {
			if (other.webshopId != null)
				return false;
		} else if (!webshopId.equals(other.webshopId))
			return false;
		return true;
	}
	
}
