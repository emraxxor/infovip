package com.github.infovip.core.es.type;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import com.github.infovip.core.date.DefaultDateFormatter;
import com.github.infovip.core.es.type.validator.RequiredField;

/**
 * Copyright (C) 2017 attila

 * @author attila
 *
 */
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
		this.setDocumentID(documentId);
	}


	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the modificationDate
	 */
	public String getModificationDate() {
		return modificationDate;
	}

	/**
	 * @param modificationDate
	 *            the modificationDate to set
	 */
	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}

	/**
	 * @return the sourceId
	 */
	public Long getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId
	 *            the sourceId to set
	 */
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @return the origMetaData
	 */
	public OrigMetaData getOrigMetaData() {
		return origMetaData;
	}

	/**
	 * @param origMetaData
	 *            the origMetaData to set
	 */
	public void setOrigMetaData(OrigMetaData origMetaData) {
		this.origMetaData = origMetaData;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the partNumber
	 */
	public String getPartNumber() {
		return partNumber;
	}
	
	/**
	 * @param partNumber
	 *            the partNumber to set
	 */
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the price
	 */
	public Long getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Long price) {
		this.price = price;
	}


	/**
	 * @return the mainCategory
	 */
	public String getMainCategory() {
		return mainCategory;
	}

	/**
	 * @param mainCategory
	 *            the mainCategory to set
	 */
	public void setMainCategory(String mainCategory) {
		this.mainCategory = mainCategory;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	
	public String getMainPictureURL() {
		return mainPictureURL;
	}
	
	public void setMainPictureURL(String mainPictureURL) {
		this.mainPictureURL = mainPictureURL;
	}
	
	/**
	 * @return the mainPicture
	 */
	public String getMainPicture() {
		return mainPicture;
	}

	/**
	 * @param mainPicture
	 *            the mainPicture to set
	 */
	public void setMainPicture(String mainPicture) {
		this.mainPicture = mainPicture;
	}

	/**
	 * @return the deliveryPrice
	 */
	public String getDeliveryPrice() {
		return deliveryPrice;
	}

	/**
	 * @param deliveryPrice
	 *            the deliveryPrice to set
	 */
	public void setDeliveryPrice(String deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}

	/**
	 * @return the deliveryTime
	 */
	public String getDeliveryTime() {
		return deliveryTime;
	}

	/**
	 * @param deliveryTime
	 *            the deliveryTime to set
	 */
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	
	public List<String> getPictureURL() {
		return pictureURL;
	}
	
	public void setPictureURL(List<String> pictureURL) {
		this.pictureURL = pictureURL;
	}

	/**
	 * @return the pictures
	 */
	public List<String> getPictures() {
		return pictures;
	}

	/**
	 * @param pictures
	 *            the pictures to set
	 */
	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}
	
	public void addParameters(String key, String data) {
		if ( !parameters.containsKey(key) ) {
			parameters.put(key, new ArrayList<String>());
		}
		parameters.get(key).add(data);
	}
	
	public void addPicture(String picture) {
		pictures.add(picture);
	}
	
	public void addPictureURL(String picture) {
		pictureURL.add(picture);
	}

	/**
	 * @return the parameters
	 */
	public Map<String, List<String>> getParameters() {
		return parameters;
	}

	/**
	 * @param parameters
	 *            the parameters to set
	 */
	public void setParameters(Map<String, List<String>> parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return the isAdultContent
	 */
	public boolean isAdultContent() {
		return isAdultContent;
	}

	/**
	 * @param isAdultContent
	 *            the isAdultContent to set
	 */
	public void setAdultContent(boolean isAdultContent) {
		this.isAdultContent = isAdultContent;
	}

	/**
	 * @return the isRacyContent
	 */
	public boolean isRacyContent() {
		return isRacyContent;
	}

	/**
	 * @param isRacyContent
	 *            the isRacyContent to set
	 */
	public void setRacyContent(boolean isRacyContent) {
		this.isRacyContent = isRacyContent;
	}

	/**
	 * @return the isApproved
	 */
	public boolean isApproved() {
		return isApproved;
	}

	/**
	 * @param isApproved
	 *            the isApproved to set
	 */
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	/**
	 * @return the isAvailable
	 */
	public boolean isAvailable() {
		return isAvailable;
	}

	/**
	 * @param isAvailable
	 *            the isAvailable to set
	 */
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	/**
	 * @return the viewCounter
	 */
	public long getViewCounter() {
		return viewCounter;
	}

	/**
	 * @param viewCounter
	 *            the viewCounter to set
	 */
	public void setViewCounter(Long viewCounter) {
		this.viewCounter = viewCounter;
	}

	/**
	 * @return the redirectionCounter
	 */
	public long getRedirectionCounter() {
		return redirectionCounter;
	}

	/**
	 * @param redirectionCounter
	 *            the redirectionCounter to set
	 */
	public void setRedirectionCounter(Long redirectionCounter) {
		this.redirectionCounter = redirectionCounter;
	}

	/**
	 * @return the scoreBoostCounter
	 */
	public long getScoreBoostCounter() {
		return scoreBoostCounter;
	}

	/**
	 * @param scoreBoostCounter
	 *            the scoreBoostCounter to set
	 */
	public void setScoreBoostCounter(Long scoreBoostCounter) {
		this.scoreBoostCounter = scoreBoostCounter;
	}

	/**
	 * @return the suggestionCounter
	 */
	public long getSuggestionCounter() {
		return suggestionCounter;
	}

	/**
	 * @param suggestionCounter
	 *            the suggestionCounter to set
	 */
	public void setSuggestionCounter(Long suggestionCounter) {
		this.suggestionCounter = suggestionCounter;
	}

	/**
	 * @return the pictureViewCounter
	 */
	public long getPictureViewCounter() {
		return pictureViewCounter;
	}

	/**
	 * @param pictureViewCounter
	 *            the pictureViewCounter to set
	 */
	public void setPictureViewCounter(Long pictureViewCounter) {
		this.pictureViewCounter = pictureViewCounter;
	}

	/**
	 * @return the userScore
	 */
	public long getUserScore() {
		return userScore;
	}

	/**
	 * @param userScore
	 *            the userScore to set
	 */
	public void setUserScore(long userScore) {
		this.userScore = userScore;
	}

	/**
	 * @return the voteNumber
	 */
	public int getVoteNumber() {
		return voteNumber;
	}

	/**
	 * @param voteNumber
	 *            the voteNumber to set
	 */
	public void setVoteNumber(int voteNumber) {
		this.voteNumber = voteNumber;
	}

	/**
	 * @return the voteSum
	 */
	public int getVoteSum() {
		return voteSum;
	}

	/**
	 * @param voteSum
	 *            the voteSum to set
	 */
	public void setVoteSum(int voteSum) {
		this.voteSum = voteSum;
	}

	/**
	 * @return the commentCounter
	 */
	public long getCommentCounter() {
		return commentCounter;
	}

	/**
	 * @param commentCounter
	 *            the commentCounter to set
	 */
	public void setCommentCounter(long commentCounter) {
		this.commentCounter = commentCounter;
	}

	
	
	/**
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	/**
	 * @return the countryCode
	 */
	public CountryCode getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}
	
	
	public void putImageMetaData(ImageMetaData meta) {
		List<ImageMetaData> d = this.imageData.stream().filter(o -> o.getImageName().equals( meta.getImageName() )).collect(Collectors.toList());
		if ( d.size() == 0 ) {
			this.imageData.add(meta);
		}
	}
	
	/**
	 * @return the imageData
	 */
	public List<ImageMetaData> getImageData() {
		return imageData;
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
	
	public List<Long> getCategories() {
		return categories;
	}
	
	public void setCategories(List<Long> categories) {
		this.categories = categories;
	}
	
	public void addParameterValue(String value) {
		if ( this.parameterValues.contains(value) == false )
			this.parameterValues.add(value);
	}
	
	public List<String> getParameterValues() {
		return parameterValues;
	}
	
	public void setParameterValues(List<String> parameterValues) {
		this.parameterValues = parameterValues;
	}
	
	public void setOld(DefaultDocumentType old) {
		this.old = old;
	}
	
	public DefaultDocumentType getOld() {
		return old;
	}
	
	
	public Long getCompanyId() {
		return companyId;
	}
	
	
	public Long getWebshopId() {
		return webshopId;
	}
	
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	
	public void setWebshopId(Long webshopId) {
		this.webshopId = webshopId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + (int) (commentCounter ^ (commentCounter >>> 32));
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((deliveryPrice == null) ? 0 : deliveryPrice.hashCode());
		result = prime * result + ((deliveryTime == null) ? 0 : deliveryTime.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (isAdultContent ? 1231 : 1237);
		result = prime * result + (isApproved ? 1231 : 1237);
		result = prime * result + (isAvailable ? 1231 : 1237);
		result = prime * result + (isRacyContent ? 1231 : 1237);
		result = prime * result + ((mainCategory == null) ? 0 : mainCategory.hashCode());
		result = prime * result + ((mainPicture == null) ? 0 : mainPicture.hashCode());
		result = prime * result + ((mainPictureURL == null) ? 0 : mainPictureURL.hashCode());
		result = prime * result + ((modificationDate == null) ? 0 : modificationDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((origMetaData == null) ? 0 : origMetaData.hashCode());
		result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
		result = prime * result + ((partNumber == null) ? 0 : partNumber.hashCode());
		result = prime * result + ((pictureURL == null) ? 0 : pictureURL.hashCode());
		result = prime * result + (int) (pictureViewCounter ^ (pictureViewCounter >>> 32));
		result = prime * result + ((pictures == null) ? 0 : pictures.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + (int) (redirectionCounter ^ (redirectionCounter >>> 32));
		result = prime * result + (int) (scoreBoostCounter ^ (scoreBoostCounter >>> 32));
		result = prime * result + ((sourceId == null) ? 0 : sourceId.hashCode());
		result = prime * result + (int) (suggestionCounter ^ (suggestionCounter >>> 32));
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + (int) (userScore ^ (userScore >>> 32));
		result = prime * result + (int) (viewCounter ^ (viewCounter >>> 32));
		result = prime * result + (int) (companyId ^ (companyId >>> 32));
		result = prime * result + (int) (webshopId ^ (webshopId >>> 32));
		result = prime * result + voteNumber;
		result = prime * result + voteSum;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefaultDocumentType other = (DefaultDocumentType) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (commentCounter != other.commentCounter)
			return false;
		if (countryCode != other.countryCode)
			return false;
		if (currency != other.currency)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (deliveryPrice == null) {
			if (other.deliveryPrice != null)
				return false;
		} else if (!deliveryPrice.equals(other.deliveryPrice))
			return false;
		if (deliveryTime == null) {
			if (other.deliveryTime != null)
				return false;
		} else if (!deliveryTime.equals(other.deliveryTime))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (isAdultContent != other.isAdultContent)
			return false;
		if (isApproved != other.isApproved)
			return false;
		if (isAvailable != other.isAvailable)
			return false;
		if (isRacyContent != other.isRacyContent)
			return false;
		if (mainCategory == null) {
			if (other.mainCategory != null)
				return false;
		} else if (!mainCategory.equals(other.mainCategory))
			return false;
		if (mainPicture == null) {
			if (other.mainPicture != null)
				return false;
		} else if (!mainPicture.equals(other.mainPicture))
			return false;
		if (mainPictureURL == null) {
			if (other.mainPictureURL != null)
				return false;
		} else if (!mainPictureURL.equals(other.mainPictureURL))
			return false;
		if (modificationDate == null) {
			if (other.modificationDate != null)
				return false;
		} else if (!modificationDate.equals(other.modificationDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (origMetaData == null) {
			if (other.origMetaData != null)
				return false;
		} else if (!origMetaData.equals(other.origMetaData))
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		if (partNumber == null) {
			if (other.partNumber != null)
				return false;
		} else if (!partNumber.equals(other.partNumber))
			return false;
		if (pictureURL == null) {
			if (other.pictureURL != null)
				return false;
		} else if (!pictureURL.equals(other.pictureURL))
			return false;
		if (pictureViewCounter != other.pictureViewCounter)
			return false;
		if (pictures == null) {
			if (other.pictures != null)
				return false;
		} else if (!pictures.equals(other.pictures))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (redirectionCounter != other.redirectionCounter)
			return false;
		if (scoreBoostCounter != other.scoreBoostCounter)
			return false;
		if (sourceId == null) {
			if (other.sourceId != null)
				return false;
		} else if (!sourceId.equals(other.sourceId))
			return false;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!webshopId.equals(other.webshopId))
			return false;
		if (sourceId == null) {
			if (other.sourceId != null)
				return false;
		} else if (!sourceId.equals(other.sourceId))
			return false;
		if (suggestionCounter != other.suggestionCounter)
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (userScore != other.userScore)
			return false;
		if (viewCounter != other.viewCounter)
			return false;
		if (voteNumber != other.voteNumber)
			return false;
		if (voteSum != other.voteSum)
			return false;
		return true;
	}
	

	// Document id is not the real part of the document, this field is responsible for updating process
	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}
	
	public DefaultDocumentType setDocumentSourceId(String documentID) {
		this.documentID = documentID;
		return this;
	}
	
	
	public String getDocumentID() {
		return documentID;
	}
	
	public void setReloadImage(boolean reloadImage) {
		this.reloadImage = reloadImage;
	}
	
	public boolean getIsReloadImage() {
		return reloadImage;
	}
	
	public boolean isReloadImage() {
		return reloadImage;
	}
	
	public boolean getIsAdultContent() {
		return this.isAdultContent;
	}
	
	public void setIsAdultContent(boolean value) {
		this.isAdultContent = value;
	}
	
	public boolean getIsRacyContent() {
		return this.isRacyContent;
	}
	
	public void setIsRacyContent(boolean value) {
		this.isRacyContent = value;
	}
	
	public boolean getIsApproved() {
		return this.isApproved;
	}
	
	public void setIsApproved(boolean value) {
		this.isApproved = value;
	}
	
	public boolean getIsAvailable() {
		return this.isAvailable;
	}
	
	public void setIsAvailable(boolean value) {
		this.isAvailable = value;
	}
	
	public boolean getIsreloadImage() {
		return this.reloadImage;
	}
	
	public void setIsreloadImage(boolean value) {
		this.reloadImage = value;
	}
	
	public boolean getRemoveDocument() {
		return this.removeDocument;
	}
	
	public void setRemoveDocument(boolean val) {
		this.removeDocument = true;
	}
	
	
	
	public boolean isElement(List<String> list) {
		return list.stream().anyMatch( e -> e.equals(partNumber) );
	}

	public static void main(String[] args) {
		DefaultDocumentType d = new DefaultDocumentType();
		System.out.println(new Gson().toJson(d, DefaultDocumentType.class));
	}

}
