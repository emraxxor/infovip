package com.github.infovip.core.web.types;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;

/**
 * 
 * @author Attila Barna
 *
 */
public class ImageData {
	
	private static final Logger logger = LogManager.getLogger(ImageData.class);
	
	public static void createThumbnail(String data, File f) {
		try {
			ImageData im = new ImageData();
			im.saveAsThumbnail(data, f);
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	public static void createLargeImage(String data, File f) {
		try {
			ImageData im = new ImageData();
			im.saveAsLarge(data, f);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public void saveAsFile(String image, Mode mode, int targetSize , File file) throws IOException {
		String[] parts = image.split(",");
		String imageString = parts[1];
		ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decodeBase64(imageString));
		BufferedImage img = ImageIO.read(bis);
		BufferedImage bi = Scalr.resize(img, mode, targetSize) ;
	    ImageIO.write(bi, "png", file);
	    bis.close();
	}
	
	public void saveAsFile(String image, Mode mode, int width, int height , File file) throws IOException {
		String[] parts = image.split(",");
		String imageString = parts[1];
		ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decodeBase64(imageString));
		BufferedImage img = ImageIO.read(bis);
		BufferedImage bi = Scalr.resize(img, mode, width, height) ;
	    ImageIO.write(bi, "png", file);
	    bis.close();
	}

	
	public void saveAsFile(String image, int targetSize , File file) throws IOException {
		saveAsFile(image, Mode.AUTOMATIC , targetSize, file);
	}

	public void saveAsThumbnail(String image, File file) throws IOException {
		saveAsFile(image, Mode.AUTOMATIC, 360, 225, file);
	}
	
	public void saveAsLarge(String image, File file) throws IOException {
		saveAsFile(image, Mode.AUTOMATIC, 800, 600, file);
	}
	
	public static File randomFileName(String path) {
		String randomFileName = RandomStringUtils.random(50, true, true);
		File f;
		
		while( ((f=new File(path + "/" + randomFileName )).exists())  ) 
			randomFileName = RandomStringUtils.random(50, true, true);
		
		return f;
	}
	
	public File generateRandomFileName(String path) {
		String randomFileName = RandomStringUtils.random(50, true, true);
		File f;
		
		while( ((f=new File(path + "/" + randomFileName )).exists())  ) 
			randomFileName = RandomStringUtils.random(50, true, true);
		
		return f;
	}
	
}
