package com.github.infovip.core.web.converter;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.github.infovip.core.web.handlers.SimpleSAXErrorHandler;

public class ValidateXML {
	
	private String xmlFilePath;

	public ValidateXML(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}
	
	public static ValidateXML create(String xmlFilePath) {
		return new ValidateXML(xmlFilePath);
	}
	
	public boolean validate() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
			db.setErrorHandler(new SimpleSAXErrorHandler());
			URL url = new URL(xmlFilePath);
			URLConnection connection = url.openConnection();
			Document doc = db.parse(connection.getInputStream());
			doc.getDocumentElement().normalize();
		} catch (ParserConfigurationException | IOException e) {
			return false;
		} catch (SAXException e) {
			return false;
		}
		return true;
	}
}
