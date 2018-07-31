package com.github.infovip.core.statistics;

public class ESStatisticsDataElement  {

	private String sessionId;
	
	private String ip;
	
	private String sessionCreated;

	public ESStatisticsDataElement(String sessionId, String ip, String sessionCreated) {
		super();
		this.sessionId = sessionId;
		this.ip = ip;
		this.sessionCreated = sessionCreated;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSessionCreated() {
		return sessionCreated;
	}

	public void setSessionCreated(String sessionCreated) {
		this.sessionCreated = sessionCreated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((sessionCreated == null) ? 0 : sessionCreated.hashCode());
		result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ESStatisticsDataElement other = (ESStatisticsDataElement) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (sessionCreated == null) {
			if (other.sessionCreated != null)
				return false;
		} else if (!sessionCreated.equals(other.sessionCreated))
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		return true;
	}

	
	
}
