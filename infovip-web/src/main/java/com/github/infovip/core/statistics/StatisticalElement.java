package com.github.infovip.core.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.github.infovip.configuration.DefaultWebAppConfiguration.ESConfiguration;
import com.github.infovip.core.date.DefaultDateFormatter;
import com.github.infovip.core.elasticsearch.ESDataElement;
import com.github.infovip.core.elasticsearch.ESOperationType;

/***
 * The statistical element for a session
 * 
 * @author attila
 *
 */
public class StatisticalElement<T extends StatisticalEventData<?>> {

	private String sessionId;
	
	private String ip;
	
	private String sessionCreated;
	
	private List<T> events;
	
	
	public StatisticalElement(HttpServletRequest request) {
		sessionId = request.getSession().getId();
		ip = request.getRemoteAddr(); // #FIX X-FORWARDED-FOR
		sessionCreated = DefaultDateFormatter.format( new Date( request.getSession().getCreationTime() ) );
		events = Collections.synchronizedList( new ArrayList<>() );
	}

	public String getSessionId() {
		return sessionId;
	}
	
	public void addEvent(T event) {
		synchronized(events) {
			event.setParent(sessionId);
			events.add(event);
		}
	}

	public List<T> getEvents() {
		return events;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getIp() {
		return ip;
	}
	
	/**
	 * Gets the data item that will be stored later
	 * @return
	 */
	public ESDataElement<ESStatisticsDataElement> getDataItem() {
		
		return new ESDataElement<ESStatisticsDataElement>() {

			@Override
			public String id() {
				return null;
			}

			@Override
			public String index() {
				return ESConfiguration.IVIP_STAT_INDEX;
			}

			@Override
			public String type() {
				return ESConfiguration.IVIP_STAT_TYPE;
			}

			@Override
			public ESOperationType operation() {
				return ESOperationType.INDEX;
			}

			@Override
			public ESStatisticsDataElement data() {
				return new ESStatisticsDataElement(sessionId, ip, sessionCreated);
			}
		};
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
	
}
