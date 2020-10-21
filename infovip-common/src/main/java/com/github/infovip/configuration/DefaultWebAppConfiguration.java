package com.github.infovip.configuration;

import java.io.Serializable;

import com.github.infovip.core.config.deprecated.TemporaryConfig;

/**
 * 
 * @author Attila Barna
 *
 */
public class DefaultWebAppConfiguration implements WebAppConfiguration {
	
    /**
     * The completely path of the images of products
     */
    public static String PRODUCT_IMAGE_PATH = "/opt/images";
    
    /**
     * User image path
     */
    public static String USER_IMAGE_PATH = "/opt/images/user";
   
    /**
     * The absolute path of the images of blogs
     */
    public static String BLOG_IMAGE_PATH = "/opt/images/blog";
   
   
    /**
     * The absolute path of the images of media
     */
    public static String MEDIA_IMAGE_PATH = "/opt/images/media";

    
    /**
     * The absolute path of the storage files
     */
    public static String STORAGE_IMAGE_PATH  = "/opt/images/files";
    
    /**
     * If it is set to true then the debug messages are displayed
     */
    public static final Boolean DEBUG = true;
    
    
    public static final Boolean PRODUCT_VERSION = false;

	
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
    
    	public static final String USERS_INDEX = "users";
    	
    	public static final String LOGS_INDEX = "logs";
    	public static final String LOGS_TYPE = "log";
    	
    	public static final String USER_MEDIA_INDEX = "user-media";
    	public static final String USER_MEDIA_PHOTO = "user-media-photo";
    	public static final String USER_MEDIA_PHOTO_COMMENT = "user-media-photo-comment";


    	
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
	
    /**
     * Some identifier to manage session
     */
    public enum BaseSessionInformation implements Serializable {
        USER_SESSION("CurrentUser"),
        AUTH_TIME("authTime"),
        REMOTE_ADDR("remoteAddr"),
        HEADER("clientHeader");

        private String value;

        private BaseSessionInformation(String val) {
            this.value = val;
        }

        /**
         *
         * @param v
         * @return
         */
        public BaseSessionInformation valueOfSession(String v) {
            for (BaseSessionInformation s : BaseSessionInformation.values()) {
                if (s.toString().equals(v)) {
                    return s;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return this.value;
        }

    }


}
