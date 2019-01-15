package com.github.infovip.configuration;

import com.github.infovip.core.config.deprecated.TemporaryConfig;

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
    	public static final String STATISTICS_INDEX = "hu-statistics-information";
    	public static final String STATISTICS_TYPE = "statistics";
    	public static final String STATISTICS_TYPE_DATA = "statistics-data";
    	public static final String PAYMENT_INDEX = "payments";
    	public static final String PAYMENT_DATA_TYPE = "payment-data";
    	public static final String BALANCE_INDEX = "balances";
    	public static final String BALANCE_DATA_TYPE = "balance-data";
    	public static final String TRANSACTION_INDEX = "transactions";
    	public static final String TRANSACTION_TYPE = "transaction-data";
    	public static final String MESSAGE_INDEX = "messages";
    	public static final String SYSTEM_MESSAGE_TYPE = "system-message";
    }
    
	public TemporaryConfig getTemporaryConfig() { throw new RuntimeException("Not implemented!"); };

}
