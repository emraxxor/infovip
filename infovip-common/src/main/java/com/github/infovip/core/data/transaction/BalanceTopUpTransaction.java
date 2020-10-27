package com.github.infovip.core.data.transaction;

import com.github.infovip.core.data.TransactionDataElement;
import com.github.infovip.core.data.type.TransactionType;
import com.github.infovip.core.date.DefaultDateFormatter;
import com.github.infovip.core.es.type.Currency;
import com.github.infovip.core.payment.PaymentType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
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

	
}
