package com.github.infovip.core.mail;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Date;

import org.apache.commons.io.IOUtils;

import com.github.infovip.core.data.DefaultUser;
import com.github.infovip.core.date.DefaultDateFormatter;
import com.github.infovip.core.date.DefaultDateFormatter.DATE_FORMAT;
import com.github.infovip.util.ResourceLoader;


public class DefaultMailConverter {

	public static String convert(DefaultUser<?> nuser, String message) {
		message = message.replaceAll("\\{useremail\\}", nuser.getUserMail() );
		message = message.replaceAll("\\{userfirstname\\}", nuser.getFirstName() );
		message = message.replaceAll("\\{userlastname\\}", nuser.getLastName() );
		message = message.replaceAll("\\{datetime\\}", DefaultDateFormatter.format(new Date(), DATE_FORMAT.STRICT_DATE_TIME) );
		message = message.replaceAll("\\{city\\}",  nuser.getCity() );
		message = message.replaceAll("\\{country\\}",  nuser.getCountry() );
		message = message.replaceAll("\\{county\\}",  nuser.getCounty() );
		return message;
	}


	public static String RegistrationTemplate(DefaultUser<?> nu) {
		InputStream is = null;
		try {
			is = ResourceLoader.load("mail/registration.txt");
			return convert( nu, IOUtils.toString(is,Charset.forName("UTF-8") ) );
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}
}
