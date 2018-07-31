package com.github.infovip.core.statistics;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.infovip.core.date.DefaultDateFormatter;

/**
 * The {@link DefaultStatisticalEvent} represents a statistical element that will be inserted into the search engine.
 * @author attila
 *
 * @param <T>
 */
public class DefaultStatisticalEvent<T extends EventFieldData> implements StatisticalEvent  {

	protected Map<String,String> headers;
	
	protected String sessionId;
	
	protected String type;
	
	protected StatisticsType stype;
	
	protected T data;
	
	protected String creationTime;
	
	public DefaultStatisticalEvent(HttpServletRequest request,T data, StatisticsType type) {
        this.headers = new HashMap<>();
        this.data = data;
        this.stype = type;
        this.type = type.toString();
        this.creationTime = DefaultDateFormatter.format(new Date());
        
        Enumeration<?> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            this.headers.put(key, value);
        }
	}
	
	public String getCreationTime() {
		return creationTime;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public StatisticsType type() {
		return this.stype;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public void setData(T data) {
		this.data = data;
	}


	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public final Map<String, String> headers() {
		return this.headers;
	}

	public T getData() {
		return data;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((headers == null) ? 0 : headers.hashCode());
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
		DefaultStatisticalEvent other = (DefaultStatisticalEvent) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (headers == null) {
			if (other.headers != null)
				return false;
		} else if (!headers.equals(other.headers))
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		return true;
	}
	
	
	

}
