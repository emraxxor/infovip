package com.github.infovip.core.web.converter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import com.thoughtworks.xstream.XStream;

import au.com.bytecode.opencsv.CSVReader;

/**
 * 
 * @author attila
 *
 */
public class CsvToXmlConverter {
	
	private byte[] byteStream;

	private CSVReader reader;

	private List<Map<String, String>> data;

	XStream xstream;
	
	private Logger logger = Logger.getLogger(CsvToXmlConverter.class);
	
	public CsvToXmlConverter() {
		data = new ArrayList<Map<String, String>>();
		xstream = new XStream();
		xstream.registerConverter(new MapEntryConverter());
		xstream.alias("list-data", Map.class);
	}
	
	public CsvToXmlConverter(String csvUrl) throws IOException {
		this();
		URL url = new URL(csvUrl);
		URLConnection connection = url.openConnection();		
		readCSV(connection.getInputStream());
	}

	public CsvToXmlConverter(InputStream stream) {
		this();
		readCSV(stream);
	}

	private String charsetName(InputStream is) {
		try {
			CharsetDetector detector = new CharsetDetector();
			CharsetMatch match = null;
			detector.setText(is);
			match = detector.detect();
			is.reset();
			return match.getName();
		} catch (IOException e) {
			logger.error(e);
		}
		return "UTF-8";

	}
	
	private void readCSV(InputStream inputStream) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			IOUtils.copy(inputStream, baos);
			byteStream = baos.toByteArray();
		    InputStream bais = new ByteArrayInputStream(byteStream);
			reader = new CSVReader( new InputStreamReader(bais, charsetName(bais) ), ';');
		} catch (IOException e1) {
			logger.error(e1);
		} 
	}
	
	public static InputStream stream(InputStream stream) throws IOException {
		CsvToXmlConverter converter = new CsvToXmlConverter(stream);
		converter.convert();
		return converter.getInputStream();
	}

	
	public static InputStream stream(String csvUrl) throws IOException {
		CsvToXmlConverter converter = new CsvToXmlConverter(csvUrl);
		converter.convert();
		return converter.getInputStream();
	}
	
	public void convert() throws IOException {
		String[] line = null;
		String[] header = reader.readNext();
		
		for(int counter = 0; counter < header.length; counter++ ) 
			header[counter] = StringUtils.stripAccents(header[counter]);
		
		
		while ((line = reader.readNext()) != null) {
			Map<String, String> o = new HashMap<String, String>();
			
			for (int i = 0; i < header.length; i++) 
				o.put(header[i], line[i]);
			
			data.add(o);
		}
	}

	public InputStream getInputStream() {
		ByteArrayOutputStream o = new ByteArrayOutputStream();
		xstream.toXML(data, o);
		return new ByteArrayInputStream(o.toByteArray());
	}
	
	public void write(OutputStream out) {
		xstream.toXML(data, out);
	}

	public void write(Writer out) {
		xstream.toXML(data, out);
	}

	@Override
	public String toString() {
		return xstream.toXML(data);
	}

	public static void main(String[] args) throws IOException {
	}
}
