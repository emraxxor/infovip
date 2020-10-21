package com.github.infovip.core.es.type;

import lombok.Data;

/**
 * 
 * @author attila
 *
 */
@Data
public class ImageMetaData {

	private String imageName;
	
	private String imageURL;
	
	private int[] dominantColor;

	private String imageType;

	private String imagePath;
	
	private int width;
	
	private int height;
	
	public ImageMetaData() {
		this.dominantColor = new int[3];
	}

	public ImageMetaData(String imageName, String imageURL,  int[] color, String imageType,String imagePath,int width, int height) {
		this();
		this.imageName = imageName;
		this.imageURL = imageURL;
		this.dominantColor = color;
		this.imageType = imageType;
		this.imagePath = imagePath;
		this.width = width;
		this.height = height;
	}

	public void setRGB(int r, int g, int b ) {
		dominantColor[0] = r;
		dominantColor[1] = g;
		dominantColor[2] = b;
	}
	
	public void setRGB(int[] v) {
		dominantColor = v;
	}
	
}
