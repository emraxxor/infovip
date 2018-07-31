package com.github.infovip.core.validator;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.google.gson.Gson;

public class GoogleCaptchaValidator {

	public static Logger logger = Logger.getLogger(GoogleCaptchaValidator.class);
	
    public static boolean validate(String gResponse,String remoteIp) {
    	CloseableHttpClient httpclient = HttpClients.createDefault();
    	HttpPost httpPost = new HttpPost("https://www.google.com/recaptcha/api/siteverify");
    	
    	List<NameValuePair> nvps = new ArrayList<NameValuePair>();
    	nvps.add(new BasicNameValuePair("secret", "6Lf9D0YUAAAAAEqQwhQEXVSIYf6UimHTtLrLhQhR"));
    	nvps.add(new BasicNameValuePair("response", gResponse));
    	nvps.add(new BasicNameValuePair("remoteip", remoteIp));
    	
    	try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse response = httpclient.execute(httpPost);
			String responseAsString = IOUtils.toString(response.getEntity().getContent(), Charset.forName("UTF-8"));
			HashMap<String,Object> data = new Gson().fromJson(responseAsString, HashMap.class);
			if ( data.containsKey("success") &&  (Boolean) data.get("success") == true ) {
				return true;
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
    	return false;
    }

}
