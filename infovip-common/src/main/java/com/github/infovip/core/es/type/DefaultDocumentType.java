package com.github.infovip.core.es.type;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import lombok.Data;

import com.github.infovip.core.date.DefaultDateFormatter;
import com.github.infovip.core.es.type.validator.RequiredField;

/**
 * Copyright (C) 2017 attila

 * @author attila
 *
 */
@Data
public class DefaultDocumentType {

	@IgnoreField
	private String documentID;

	@IgnoreField
	private boolean reloadImage;

	@IgnoreField
	private boolean removeDocument;
	
	@RequiredField
	@Changeable
	private String date;

	@RequiredField
	@Changeable
	private String modificationDate;

	@RequiredField
	private Long sourceId;
	
	@RequiredField
	private Long companyId;
	
	@RequiredField
	private Long webshopId;

	@RequiredField
	private OrigMetaData origMetaData;

	@RequiredField
	private String name;

	@RequiredField
	private String partNumber;

	/**@RequiredField**/
	private String description;

	private Long price;

	private Currency currency;

	private CountryCode countryCode;

	private String mainCategory;

	/**@RequiredField**/
	private String category;

	@RequiredField
	private String url;

	private String mainPicture;
	
	@RequiredField
	private String mainPictureURL;

	private String deliveryPrice;

	private String deliveryTime;

	private List<String> pictureURL;
	
	private List<String> pictures;

	@OnlyWebModified
	private Map<String, List<String>> parameters;

	@OnlyWebModified
	private boolean isAdultContent;
	
	@OnlyWebModified
	private boolean isRacyContent;

	@OnlyWebModified
	private boolean isApproved;

	@OnlyWebModified
	private boolean isAvailable;

	@OnlyWebModified
	private long viewCounter;

	@OnlyWebModified
	private long redirectionCounter;

	@OnlyWebModified
	private long scoreBoostCounter;

	@OnlyWebModified
	private long suggestionCounter;

	@OnlyWebModified
	private long pictureViewCounter;

	@OnlyWebModified
	private long userScore;

	@OnlyWebModified
	private int voteNumber;

	@OnlyWebModified
	private int voteSum;

	@OnlyWebModified
	private long commentCounter;

	@IgnoreField
	private List<Long> categories;
	
	@IgnoreField
	private List<ImageMetaData> imageData;
	
	@IgnoreField
	private List<String> parameterValues;
	
	@IgnoreField
	private DefaultDocumentType old;

	public DefaultDocumentType() {
		date = DefaultDateFormatter.format(new Date());
		modificationDate = date;
		origMetaData = new OrigMetaData();
		name = "";
		description = "";
		currency = Currency.HUF;
		countryCode = CountryCode.HU;
		mainCategory = "all";
		deliveryPrice = "";
		deliveryTime = "";
		pictures = new LinkedList<String>();
		pictureURL = new LinkedList<String>();
		parameters = new LinkedHashMap<String, List<String>>();
		imageData = new LinkedList<ImageMetaData>();
		categories = new LinkedList<>();
		parameterValues = new LinkedList<>();
		isAdultContent = false;
		isRacyContent = false;
		isApproved = true;
		isAvailable = true;
		viewCounter = 0;
		redirectionCounter = 0;
		scoreBoostCounter = 0;
		//scoreBoostCounter = 1000L;
		suggestionCounter = 0;
		pictureViewCounter = 0;
		userScore = 0;
		voteNumber = 0;
		voteSum = 0;
		commentCounter = 0;
		reloadImage = false;
	}
	
	public DefaultDocumentType(String documentId) {
		this();
		this.documentID = documentId;
	}

	
	public void addParameters(String key, String data) {
		if ( !parameters.containsKey(key) ) 
			parameters.put(key, new ArrayList<String>());

		parameters.get(key).add(data);
	}
	
	public void addPicture(String picture) {
		pictures.add(picture);
	}
	
	public void addPictureURL(String picture) {
		pictureURL.add(picture);
	}

	
	public void putImageMetaData(ImageMetaData meta) {
		List<ImageMetaData> d = this.imageData.stream().filter(o -> o.getImageName().equals( meta.getImageName() )).collect(Collectors.toList());
		if ( d.size() == 0 ) 
			this.imageData.add(meta);
	}
	
	public void clearImageMetaData() {
		this.imageData.clear();
	}
	
	/**
	 * @param imageData the imageData to set
	 */
	public void setImageData(List<ImageMetaData> imageData) {
		this.imageData = imageData;
	}
	
	public void addCategory(Long id) {
		this.categories.add(id);
	}
	
	public void removeCategory(Long id) {
		this.categories.remove(id);
	}
	
	public void addParameterValue(String value) {
		if ( this.parameterValues.contains(value) == false )
			this.parameterValues.add(value);
	}
	
}
