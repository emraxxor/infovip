package com.github.infovip.core.es.type;

import java.util.Arrays;

/**
 * 
 * @author attila
 *
 */
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
	
	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * @return the imageURL
	 */
	public String getImageURL() {
		return imageURL;
	}

	/**
	 * @param imageURL the imageURL to set
	 */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}


	public int[] getDominantColor() {
		return dominantColor;
	}
	
	public void setDominantColor(int[] dominantColor) {
		this.dominantColor = dominantColor;
	}



	/**
	 * @return the imageType
	 */
	public String getImageType() {
		return imageType;
	}

	/**
	 * @param imageType the imageType to set
	 */
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	
	

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(dominantColor);
		result = prime * result + height;
		result = prime * result + ((imageName == null) ? 0 : imageName.hashCode());
		result = prime * result + ((imagePath == null) ? 0 : imagePath.hashCode());
		result = prime * result + ((imageType == null) ? 0 : imageType.hashCode());
		result = prime * result + ((imageURL == null) ? 0 : imageURL.hashCode());
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImageMetaData other = (ImageMetaData) obj;
		if (!Arrays.equals(dominantColor, other.dominantColor))
			return false;
		if (height != other.height)
			return false;
		if (imageName == null) {
			if (other.imageName != null)
				return false;
		} else if (!imageName.equals(other.imageName))
			return false;
		if (imagePath == null) {
			if (other.imagePath != null)
				return false;
		} else if (!imagePath.equals(other.imagePath))
			return false;
		if (imageType == null) {
			if (other.imageType != null)
				return false;
		} else if (!imageType.equals(other.imageType))
			return false;
		if (imageURL == null) {
			if (other.imageURL != null)
				return false;
		} else if (!imageURL.equals(other.imageURL))
			return false;
		if (width != other.width)
			return false;
		return true;
	}

	
	
	
	
}
