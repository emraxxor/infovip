package com.github.infovip.core.payment;

import java.util.Date;

import com.github.infovip.core.data.BaseDataElement;
import com.github.infovip.core.date.DefaultDateFormatter;

public class DefaultPaymentElement<T>  extends BaseDataElement {

	/**
	 * Type of the document
	 */
	private String paymentType;
	
	/**
	 * The id of the company
	 */
	private Long companyId;
	
	/**
	 * The general identifier of the user !
	 * It never can be empty!
	 */
	private Long userId;
	
	/**
	 * The identifier of the user to whom the identifier belongs.
	 * Sometimes it can be empty!!
	 */
	private String userIdentifier;
	
	/**
	 * Status of the current payment
	 */
	private String paymentStatus;

	/**
	 * General payment identifier
	 */
	private Long payId;
	
	/**
	 * !!! Only the supported date format is allowed !!!!
	 * @see DefaultDateFormatter.format()
	 */
	private String date;

	/**
	 * The payment data
	 */
	private T data;
	
	
	
	public DefaultPaymentElement(PaymentType type, PaymentStatus status, T data) {
		this.data = data;
		this.date = DefaultDateFormatter.format(new Date());
		this.paymentType = type.val();
		this.paymentStatus = status.val();
	}
	
	public DefaultPaymentElement(PaymentType type, PaymentStatus status, Long companyId, Long userId, String userIdentifier, Long payId, T data) {
		this(type,status,data);
		this.companyId = companyId;
		this.userId = userId;
		this.userIdentifier = userIdentifier;
		this.payId = payId;
	}

	
	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * @return the companyId
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the userIdentifier
	 */
	public String getUserIdentifier() {
		return userIdentifier;
	}

	/**
	 * @param userIdentifier the userIdentifier to set
	 */
	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	/**
	 * @return the payId
	 */
	public Long getPayId() {
		return payId;
	}

	/**
	 * @param payId the payId to set
	 */
	public void setPayId(Long payId) {
		this.payId = payId;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	
	public String getPaymentStatus() {
		return paymentStatus;
	}
	
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((payId == null) ? 0 : payId.hashCode());
		result = prime * result + ((paymentStatus == null) ? 0 : paymentStatus.hashCode());
		result = prime * result + ((paymentType == null) ? 0 : paymentType.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userIdentifier == null) ? 0 : userIdentifier.hashCode());
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
		DefaultPaymentElement other = (DefaultPaymentElement) obj;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (payId == null) {
			if (other.payId != null)
				return false;
		} else if (!payId.equals(other.payId))
			return false;
		if (paymentStatus == null) {
			if (other.paymentStatus != null)
				return false;
		} else if (!paymentStatus.equals(other.paymentStatus))
			return false;
		if (paymentType == null) {
			if (other.paymentType != null)
				return false;
		} else if (!paymentType.equals(other.paymentType))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userIdentifier == null) {
			if (other.userIdentifier != null)
				return false;
		} else if (!userIdentifier.equals(other.userIdentifier))
			return false;
		return true;
	}

}
