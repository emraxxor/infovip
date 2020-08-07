package com.github.infovip.core.fs;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;

import com.github.infovip.core.Configuration;
import com.github.infovip.core.web.types.ImageData;

/**
 * 
 * @author Attila Barna
 *
 */
public class InternalFSManager {

	public static class TYPE {
		public static final String STORAGE_IMAGE = "STORAGE";
		public static final String USER_IMAGE = "USER";

	}
	
	
	private String type;
	
	private String folder;
	
	
	/**
	 * 
	 * @param type
	 * @see InternalFSManager.TYPE
	 */
	public InternalFSManager(String type) {
		this.type = type;
		
		if ( type == TYPE.USER_IMAGE ) {
			folder = Configuration.USER_IMAGE_PATH;
		} else {
			folder = Configuration.STORAGE_IMAGE_PATH;
		}
	}
	
	public List<FileElement> list() {
		return FileUtils.listFiles(new File(folder),null,false).stream().map( o->new FileElement(o,type)).collect(Collectors.toList());
	}
	
	public boolean exists(String name) {
		return new File(folder+ "/" + name ).exists();
	}
	
	public boolean delete(String name) {
		if ( exists(name) ) {
			 return new File(folder+"/"+name).delete();
		}
		return false;
	}
	
	/**
	 * 
	 * @param name
	 * Name of the file, it must be unique!
	 * @param data
	 * Base64 encoded data
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String put(String name, String data) throws FileNotFoundException, IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decodeBase64(data));
		IOUtils.copy(bis, new FileOutputStream(new File(folder + "/" + name)));
		return name;
	}
	
	/**
	 * Gets the as Base64 encoded data
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public String get(String name) throws IOException {
		byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(new File(folder + "/" + name)));
		return new String(encoded, StandardCharsets.US_ASCII);
	}
	
	/**
	 * 
	 * @param name
	 * Name of the file, it must be unique! Use {@link #generateRandomFileName(String)} by default.
	 * @param data
	 * Base64 encoded data
	 * @return
	 * @throws IOException
	 */
	public String putImage(String name, String data) throws IOException {
		ImageData imd = new ImageData();
		File file = new File(folder + "/" + name );
		file.createNewFile();
		imd.saveAsLarge(data, file);
		return file.getName();
	}
	

	/**
	 * 
	 * @param data
	 * Base64 encoded data
	 * @return
	 * @throws IOException
	 */
	public String putImage(String data) throws IOException {
		ImageData imd = new ImageData();
		File file = imd.generateRandomFileName(folder);
		file.createNewFile();
		imd.saveAsLarge(data, file);
		return file.getName();
	}
	
	/**
	 * Name of the file
	 * @param name
	 * @return
	 */
	public boolean removeImage(String name) {
		boolean status = true;
		
		if ( name != null && name.equalsIgnoreCase("") == false && name.length() > 10) {
			File old = new File(folder+"/"+name);
			File oldLarge = new File(folder+"/"+name+"_f");
		
			if ( old.exists() ) 
				status = status && old.delete();
		
			if ( oldLarge.exists() ) 
				status = status && oldLarge.delete();
		}
		
		return status;

	}
	
	/**
	 * Generates a random filename in the given path.
	 * @param path
	 * @return
	 */
	public File generateRandomFileName(String path) {
		String randomFileName = RandomStringUtils.random(50, true, true);
		File f;
		
		while( ((f=new File(path + "/" + randomFileName )).exists())  ) 
			randomFileName = RandomStringUtils.random(50, true, true);
		
		return f;
	}

}
