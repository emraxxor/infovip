package com.github.infovip.internal.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.github.infovip.core.web.handlers.SimpleSAXErrorHandler;

/**
 * 
 * @author attila
 *
 */
public class XMLNodeNameReader {

	private String xmlFilePath;
	
	private Map<String,String> nodeNames;	
	
	private InputStream inputStream;
	
	public XMLNodeNameReader(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
		this.nodeNames = new LinkedHashMap<>();
	}
	
	public XMLNodeNameReader(InputStream inputStream) {
		this.inputStream = inputStream;
		this.nodeNames = new LinkedHashMap<>();
	}

	
	public static XMLNodeNameReader create(String xmlFilePath) throws Exception {
		XMLNodeNameReader reader = new XMLNodeNameReader(xmlFilePath);
		reader.parse();
		return reader;
	}
	
	public static XMLNodeNameReader create(InputStream input) throws Exception {
		XMLNodeNameReader reader = new XMLNodeNameReader(input);
		reader.parse();
		return reader;
	}
	
	public void parse() throws Exception {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			db.setErrorHandler(new SimpleSAXErrorHandler());
			Document doc = null;

			if ( inputStream == null && xmlFilePath != null ) {
				URL url = new URL(xmlFilePath);
				URLConnection connection = url.openConnection();
				doc = db.parse(connection.getInputStream());
			} else if ( inputStream != null && xmlFilePath == null ) {
				doc = db.parse(inputStream);
			}
			
			doc.getDocumentElement().normalize();
			NodeList nodes = doc.getDocumentElement().getFirstChild().getNextSibling().getChildNodes();
			this.nodeNames.put("", "");
			for (int i = 0; i < nodes.getLength(); i++) {
				  Node node = nodes.item(i);
				  if (node instanceof Element) {
				    Element childElement = (Element) node;
				    this.nodeNames.put(childElement.getNodeName(),childElement.getTextContent());
				  }
			}
		} catch (IOException | ParserConfigurationException | SAXException e) {
			throw e;
		}
	}
	
	public Map<String,String> getNodeNames() {
		return nodeNames;
	}
}
