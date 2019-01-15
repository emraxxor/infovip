package com.github.infovip.core.basic.jsp.tags;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.apache.commons.codec.binary.Base64;
import org.apache.lucene.util.automaton.RegExp;

import com.google.gson.Gson;

public class Functions {

	public static String encode(String val) throws UnsupportedEncodingException {
		return URLEncoder.encode(val,"UTF-8").replace("+", "%20");
	}
	
	public static String encodeEx(String val)  {
		try {
			return URLEncoder.encode(val,"UTF-8").replace("+", "%20");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static Date getClassBuildTime() {
	    Date d = null;
	    Class<?> currentClass = new Object() {}.getClass().getEnclosingClass();
	    URL resource = currentClass.getResource(currentClass.getSimpleName() + ".class");
	    if (resource != null) {
	        if (resource.getProtocol().equals("file")) {
	            try {
	                d = new Date(new File(resource.toURI()).lastModified());
	            } catch (URISyntaxException ignored) { }
	        } else if (resource.getProtocol().equals("jar")) {
	            String path = resource.getPath();
	            d = new Date( new File(path.substring(5, path.indexOf("!"))).lastModified() );    
	        } else if (resource.getProtocol().equals("zip")) {
	            String path = resource.getPath();
	            File jarFileOnDisk = new File(path.substring(0, path.indexOf("!")));
	            //long jfodLastModifiedLong = jarFileOnDisk.lastModified ();
	            //Date jfodLasModifiedDate = new Date(jfodLastModifiedLong);
	            try(JarFile jf = new JarFile (jarFileOnDisk)) {
	                ZipEntry ze = jf.getEntry (path.substring(path.indexOf("!") + 2));//Skip the ! and the /
	                long zeTimeLong = ze.getTime ();
	                Date zeTimeDate = new Date(zeTimeLong);
	                d = zeTimeDate;
	            } catch (IOException|RuntimeException ignored) { }
	        }
	    }
	    return d;
	}

	
	public static String byteArrayToString(byte[] data) {
		return new String(data);
	}
	
	public static List convertToUrl(String data) {
		 return new Gson().fromJson(new String(Base64.decodeBase64(data)), List.class);
	}
	
	public static String toUrlFriendly(String val)  {
		  val = val.trim(); 
		  val = val.toLowerCase();
		  
		  String from = "ãàáäâẽèéëêìíïîõòóöôùúüûñçőÃÀÁÄÂẼÈÉËÊÌÍÏÎÕÒÓÖÔÙÚÜÛÑÇŐ·/_,:;";
		  String to   = "aaaaaeeeeeiiiiooooouuuuncoAAAAAEEEEEIIIIOOOOOUUUUNCO------";
		  int i = 0;
		  int l = from.length();
		  for (; i<l ; i++) 
		    val = val.replace(from.charAt(i), to.charAt(i));
		  

		  val = val.replaceAll("[^a-zA-Z0-9 -]", "") // remove invalid chars
				.replaceAll("\\s+", "-") // collapse whitespace and replace by -
				.replaceAll("-+", "-"); // collapse dashes

		  return val;
	}
}
