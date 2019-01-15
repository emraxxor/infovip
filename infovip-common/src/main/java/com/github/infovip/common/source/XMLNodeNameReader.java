package com.github.infovip.common.source;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.mozilla.universalchardet.UniversalDetector;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.github.infovip.core.web.converter.SimpleSAXErrorHandler;
import com.github.infovip.core.xml.InvalidXmlCharacterFilter;


/**
 * 
 * @author attila
 *
 */
public class XMLNodeNameReader {

	private String xmlFilePath;
	
	private List<Map<String,String>> nodeNames;	
	private int numberOfRecords;	
	
	private InputStream inputStream;
	
	public XMLNodeNameReader(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
		this.nodeNames = new LinkedList<Map<String,String>>();
		this.numberOfRecords = 0;
	}
	
	public XMLNodeNameReader(InputStream inputStream) {
		this.inputStream = inputStream;
		this.nodeNames = new LinkedList<Map<String,String>>();
		this.numberOfRecords = 0;
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
	
	private InputStream decompressStream(InputStream input) throws IOException {
	     PushbackInputStream pb = new PushbackInputStream( input, 2 ); //we need a pushbackstream to look ahead
	     byte [] signature = new byte[2];
	     int len = pb.read( signature ); //read the signature
	     pb.unread( signature, 0, len ); //push back the signature to the stream
	     if( signature[ 0 ] == (byte) 0x1f && signature[ 1 ] == (byte) 0x8b ) //check if matches standard gzip magic number
	       return new GZIPInputStream( pb );
	     else 
	       return pb;
	}

    public String getDecoder(InputStream inputStream) throws IOException {
    	String encoding = null;
		byte[] buf = new byte[4096];
		UniversalDetector detector = new UniversalDetector(null);
		int nread;

		while ((nread = inputStream.read(buf)) > 0 && !detector.isDone()) {
			detector.handleData(buf, 0, nread);
		}

		detector.dataEnd();
		encoding = detector.getDetectedCharset();
		detector.reset();
		
		inputStream.reset();
		return encoding;
    }

	
	public void parse() throws Exception {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			db.setErrorHandler(new SimpleSAXErrorHandler());
			Document doc = null;
			int cnt = 0;
			
			if ( inputStream == null && xmlFilePath != null ) {
				URL url = new URL(xmlFilePath);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				byte[] data = IOUtils.toByteArray( new BOMInputStream( connection.getInputStream() ));
				String charset = getDecoder(new ByteArrayInputStream(data));
				
				if ( charset == null ) charset = "UTF-8";
				
				Reader reader = new BufferedReader(new InputStreamReader(decompressStream( new ByteArrayInputStream(data) ), charset));
				InvalidXmlCharacterFilter filter = new InvalidXmlCharacterFilter(reader);
				InputSource is = new InputSource(filter);
				doc = db.parse(is);
			} else if ( inputStream != null && xmlFilePath == null ) {
				byte[] data = IOUtils.toByteArray(new BOMInputStream( inputStream ));
				String charset = getDecoder(new ByteArrayInputStream(data));

				if ( charset == null ) charset = "UTF-8";
				Reader reader = new BufferedReader(new InputStreamReader(decompressStream( new ByteArrayInputStream(data) ),charset));
				InvalidXmlCharacterFilter filter = new InvalidXmlCharacterFilter(reader);
				InputSource is = new InputSource(filter);
				doc = db.parse(is);
			}
			
			doc.getDocumentElement().normalize();
			NodeList nodes = doc.getDocumentElement().getChildNodes();
			numberOfRecords = nodes.getLength();
			
			for( int j = 0; j < nodes.getLength(); j++) {
				NodeList elems = null;
				
				if ( nodes.item(j).getNextSibling() == null )  {
					if ( nodes.getLength() != 2 ) {
						continue;
					} else {
						elems = nodes.item(0).getChildNodes();
					}
				}  
				
				if ( elems == null ) {
					elems = nodes.item(j).getNextSibling().getChildNodes();
				}
				
				Map<String, String> cols = new LinkedHashMap<>();
				//NodeList nodes = doc.getDocumentElement().getFirstChild().getNextSibling().getChildNodes();
				
				cols.put("", "");
				for (int i = 0; i < elems.getLength(); i++) {
					  Node node = elems.item(i);
					  if (node instanceof Element) {
						Element childElement = (Element) node;
						cols.put(childElement.getNodeName(),childElement.getTextContent());
					  }
				}
				
				if ( cols.size() > 1 ) {
					nodeNames.add(cols);
				
					if ( ++cnt >= 5 ) 
						break;
				}
			}
		} catch (IOException | ParserConfigurationException | SAXException e) {
			throw e;
		}
	}
	
	public List<Map<String,String>> getNodeNames() {
		return nodeNames;
	}
	
	public Integer getNumberOfRecords() {
		return numberOfRecords;
	}

}
