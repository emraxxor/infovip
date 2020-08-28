package com.github.infovip.core.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * Helper class for managing dates.
 * 
 * @author Attila Barna
 *
 */
public class DefaultDateFormatter {

	private static final Logger logger = Logger.getLogger(DefaultDateFormatter.class);
	
	public static enum DATE_FORMAT {
		STRICT_DATE_FORMAT("yyyy-MM-dd'T'HH:mm:ss.SSS"),
		STRICT_DATE_TIME("yyyy-MM-dd HH:mm:ss"),
		YEAR_MONTH("yyyy-MM"),
		YEAR_MONTH_DAY("yyyy-MM-dd")
		;
		
		private String value;
		
		private DATE_FORMAT(String val) {
			this.value = val;
		}
		
        @Override
        public String toString() {
            return this.value;
        }
		
	}
	
	public static Timestamp timestamp() {
		return new Timestamp(new Date().getTime());
	}
	
	public static Timestamp timestamp(String date, DATE_FORMAT df) {
		return (Timestamp) parse(date, df);
	}

	
	public static String current() {
		return format(new Date());
	}
	
	public static String format(Date date) {
		return DefaultDateFormatter.format(date, DATE_FORMAT.STRICT_DATE_FORMAT);
	}
	
	public static String format(Timestamp date) {
		return DefaultDateFormatter.format(date, DATE_FORMAT.STRICT_DATE_FORMAT);
	}
	
	
	public static String format(Date date, DATE_FORMAT df) {
		SimpleDateFormat sdf = new SimpleDateFormat(df.value);
		return sdf.format(date);
	}
	
	public static String format(Timestamp date, DATE_FORMAT df) {
		SimpleDateFormat sdf = new SimpleDateFormat(df.value);
		return sdf.format(date);
	}
	
	public static String format(String strictDateTime,DATE_FORMAT df) {
		SimpleDateFormat sdf = new SimpleDateFormat(df.value);
		try {
			return format( sdf.parse(strictDateTime) , DATE_FORMAT.STRICT_DATE_FORMAT );
		} catch (ParseException e) {
			logger.warn(e.getMessage(), e);
		} 
		return null;
	}
	
	public static String format(String strictDateTime,DATE_FORMAT from,DATE_FORMAT to) {
		SimpleDateFormat sdf = new SimpleDateFormat(from.value);
		try {
			return format( sdf.parse(strictDateTime) , to);
		} catch (ParseException e) {
			logger.warn(e.getMessage(), e);
		} 
		return null;
	}

	public static Date parse(String strictDateTime, DATE_FORMAT format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format.value);
		try {
			return sdf.parse(strictDateTime); 
		} catch (ParseException e) {
			if ( strictDateTime.contains("T") ) {
				SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT.YEAR_MONTH_DAY.value);
				try {
					return sdf1.parse(strictDateTime.split("T")[0]);
				} catch (ParseException e1) {
					return null;
				}
			}
			logger.warn(e.getMessage(), e);
		} 
		return null;
	}
	
	public static Date createDate(Date d, int field,  int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(field, amount);
		return cal.getTime();
	}

	
	public static void main(String[] args) {
		System.out.println(DefaultDateFormatter.format("2017-10-15 12:33:33", DATE_FORMAT.STRICT_DATE_TIME, DATE_FORMAT.YEAR_MONTH));
		System.out.println(DefaultDateFormatter.format( new Date(),DATE_FORMAT.YEAR_MONTH) );
	}
}
