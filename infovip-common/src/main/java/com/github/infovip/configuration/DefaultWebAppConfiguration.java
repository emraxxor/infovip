package com.github.infovip.configuration;

import com.github.infovip.core.config.deprecated.TemporaryConfig;

/**
 * 
 * @author Attila Barna
 *
 */
public class DefaultWebAppConfiguration implements WebAppConfiguration {
	
    public static final class ESConfiguration {
    	public static final String INDEX = "hu-product"; 
    	public static final String TYPE = "products";
    	public static final Integer BULK_SIZE = 5000;
    	public static final Integer QUERY_MAX_SIZE = 5000;
    	public static final Integer QUEUE_MAX_SIZE = 50000;
    	public static final Long QUERY_SLEEP_TIME = 5000L;
    	public static final Long BULK_WAIT_TIME = 15000L;
    	public static final Long SCROLL_TIME_VALUE = 30000L;
    	public static final String TIMELINE_INDEX = "timeline";
    	public static final String TIMELINE_TYPE = "timeline-data";
    	public static final String MESSAGE_INDEX = "messages";
    	public static final String ACTIVITY_POST_INDEX = "activity-post";
    	public static final String ACTIVITY_POST_TYPE = "data";
    	public static final String ACTIVITY_LIKE_INDEX = "activity-post";
    	public static final String ACTIVITY_LIKE_TYPE = "data";
    	public static final String SYSTEM_MESSAGE_TYPE = "system-message";
    	public static final String IVIP_STAT_INDEX = "ivip-stat";
    	public static final String IVIP_STAT_TYPE = "ivip-stat-data";
    	public static final String PAYMENT_INDEX = "payments";
    	public static final String PAYMENT_DATA_TYPE = "payment-data";
    	
    	/**
    	 * 
    	 * @author attila
    	 * Shards
    	 */
    	public static final class ES_ROUTING {
    		public static final String ROUTING_1 = "1";
    		public static final String ROUTING_2 = "2";
    		public static final String ROUTING_3 = "3";
    		public static final String ROUTING_4 = "4";
    		public static final String ROUTING_5 = "5";
    	}
    }
    
    public static final class MODULE_PATH {
    }

    
	public TemporaryConfig getTemporaryConfig() { throw new RuntimeException("Not implemented!"); };

}
