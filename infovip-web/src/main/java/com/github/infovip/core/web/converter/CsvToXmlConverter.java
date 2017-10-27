package com.github.infovip.core.web.converter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.thoughtworks.xstream.XStream;

import au.com.bytecode.opencsv.CSVReader;

/**
 * 
 * @author attila
 *
 */
public class CsvToXmlConverter {

	private CSVReader reader;

	private List<Map<String, String>> data;

	XStream xstream;

	public CsvToXmlConverter(String csvUrl) throws IOException {
		URL url = new URL(csvUrl);
		URLConnection connection = url.openConnection();
		reader = new CSVReader(new InputStreamReader(connection.getInputStream()),';');		
		data = new ArrayList<Map<String, String>>();
		xstream = new XStream();
		xstream.registerConverter(new MapEntryConverter());
		xstream.alias("list-data", Map.class);
	}

	public CsvToXmlConverter(InputStream stream) {
		reader = new CSVReader(new InputStreamReader(stream,Charset.forName("UTF-8")),';');		
		data = new ArrayList<Map<String, String>>();
		xstream = new XStream();
		xstream.registerConverter(new MapEntryConverter());
		xstream.alias("list-data", Map.class);
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
		while ((line = reader.readNext()) != null) {
			Map<String, String> o = new HashMap<String, String>();
			for (int i = 0; i < header.length; i++) {
				o.put(header[i], line[i]);
			}
			data.add(o);
		}
	}

	public InputStream getInputStream() {
		ByteArrayOutputStream o = new ByteArrayOutputStream();
		write(o);
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
