package com.github.infovip.spring.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.infovip.core.Configuration;

@Service
public class StorageService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -430256389432706027L;
	
	@Autowired
	private Configuration configuration;
	
	
	private Logger logger = Logger.getLogger(StorageService.class);
	
	public StorageService() {
		// TODO Auto-generated constructor stub
	}
	
	public File storeFile(byte[] data) {
		File f = generateRandomFileName();
		FileOutputStream fos = null;
		try {
			f.createNewFile();
			fos = new FileOutputStream(f);
			fos.write(data);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if ( fos != null ) {
				try {
					fos.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}
		}
		return f;
	}

	public File storeFile(String base64data) {
		return storeFile(Base64.decodeBase64(base64data));
	}
	
	public File generateRandomFileName() {
		String path = Configuration.STORAGE_IMAGE_PATH ;
		String randomFileName = RandomStringUtils.random(50, true, true);
		File f;
		
		while( ((f=new File(path + "/" + randomFileName )).exists())  ) 
			randomFileName = RandomStringUtils.random(50, true, true);
		
		return f;
	}

}
