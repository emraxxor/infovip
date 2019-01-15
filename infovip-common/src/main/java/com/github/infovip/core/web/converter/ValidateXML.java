package com.github.infovip.core.web.converter;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.github.infovip.core.xml.InvalidXmlCharacterFilter;

/**
 * 
 * @author Attila Barna
 * @category infovip.core.validator
 *
 */
public class ValidateXML {
	
	private String xmlFilePath;
	
	private static final Logger logger = Logger.getLogger(ValidateXML.class);

	public ValidateXML() {}
	
	public ValidateXML(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}
	
	public static ValidateXML create(String xmlFilePath) {
		return new ValidateXML(xmlFilePath);
	}

	public boolean validate() {	
		URL url;
		URLConnection connection;
		try {
			url = new URL(xmlFilePath);
			connection = url.openConnection();
			return validate(connection.getInputStream());
		} catch (MalformedURLException e) {
			logger.error(e);;
		} catch (IOException e) {
			logger.error(e);;
		}
		return false;
	}
	
	public static boolean validate(InputStream stream) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
			db.setErrorHandler(new SimpleSAXErrorHandler());
			Document doc = db.parse(stream);
			doc.getDocumentElement().normalize();
			if(stream.markSupported()) {
				stream.reset();
			}
		} catch (ParserConfigurationException | IOException e) {
			logger.error(e);;
			return false;
		} catch (SAXException e) {
			logger.error(e);;
			return false;
		}
		
		return true;
	}
}
