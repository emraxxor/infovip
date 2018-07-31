package com.github.infovip.core.data.transaction;

import com.github.infovip.core.data.TransactionDataElement;
import com.github.infovip.core.data.type.TransactionType;
import com.github.infovip.core.date.DefaultDateFormatter;
import com.github.infovip.core.es.type.Currency;
import com.github.infovip.core.payment.PaymentType;

public class BalanceTopUpTransaction<T> extends TransactionDataElement {

	/**
	 * The internal identifier of the payment
	 */
	private String paymentId;
	
	/**
	 * {@link PaymentType#val()}
	 */
	private String paymentType;
	
	/**
	 * General payment identifier
	 */
	private Long payId;

	/**
	 * @use {@link DefaultDateFormatter}
	 */
	private String paymentTime;
	
	
	/**
	 * The general identifier of the user !
	 * It never can be empty!
	 */
	private Long userId;
	

	private T barionTransactionResponse;
	
	
	
	public BalanceTopUpTransaction(
			Long companyId, 
			TransactionType type,
			String creationTime, 
			Double currentBalance, 
			Double amount, 
			Long payId, 
			String paymentTime, 
			PaymentType paymentType ,
			Currency currency
	) {
		super(companyId, type, creationTime, currentBalance, amount,currency);
		this.payId = payId;
		this.paymentTime = paymentTime;
		this.paymentType = paymentType.val();
	}

	/**
	 * @return the paymentId
	 */
	public String getPaymentId() {
		return paymentId;
	}



	/**
	 * @param paymentId the paymentId to set
	 */
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
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
	 * @return the paymentTime
	 */
	public String getPaymentTime() {
		return paymentTime;
	}



	/**
	 * @param paymentTime the paymentTime to set
	 */
	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
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
	 * @return the barionTransactionResponse
	 */
	public T getBarionTransactionResponse() {
		return barionTransactionResponse;
	}

	/**
	 * @param barionTransactionResponse the barionTransactionResponse to set
	 */
	public void setBarionTransactionResponse(T barionTransactionResponse) {
		this.barionTransactionResponse = barionTransactionResponse;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((payId == null) ? 0 : payId.hashCode());
		result = prime * result + ((paymentId == null) ? 0 : paymentId.hashCode());
		result = prime * result + ((paymentTime == null) ? 0 : paymentTime.hashCode());
		result = prime * result + ((paymentType == null) ? 0 : paymentType.hashCode());
		result = prime * result + ((barionTransactionResponse == null) ? 0 : barionTransactionResponse.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BalanceTopUpTransaction other = (BalanceTopUpTransaction) obj;
		if (payId == null) {
			if (other.payId != null)
				return false;
		} else if (!payId.equals(other.payId))
			return false;
		if (paymentId == null) {
			if (other.paymentId != null)
				return false;
		} else if (!paymentId.equals(other.paymentId))
			return false;
		if (paymentTime == null) {
			if (other.paymentTime != null)
				return false;
		} else if (!paymentTime.equals(other.paymentTime))
			return false;
		if (paymentType == null) {
			if (other.paymentType != null)
				return false;
		} else if (!paymentType.equals(other.paymentType))
			return false;
		if (barionTransactionResponse == null) {
			if (other.barionTransactionResponse != null)
				return false;
		} else if (!barionTransactionResponse.equals(other.barionTransactionResponse))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
}
