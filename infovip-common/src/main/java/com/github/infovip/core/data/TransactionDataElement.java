package com.github.infovip.core.data;

import java.util.Date;

import com.github.infovip.core.data.type.TransactionType;
import com.github.infovip.core.date.DefaultDateFormatter;
import com.github.infovip.core.es.type.Currency;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransactionDataElement extends BaseDataElement {

	private Long clientId;

	/**
	 * {@link TransactionType#val()}
	 */
	private String transactionType;
	
	/**
	 * @use DefaultDateFormatter.format(new Date());
	 */
	private String creationTime;
	
	/**
	 * The new balance, calculated data, it must be equal with the actual ballance of the company !
	 */
	private Double currentBalance;
	
	/**
	 * The amount of the transaction, which changes the current balance.
	 */
	private Double amount;
	
	/**
	 * Currency of the transaction
	 */
	private String currency;
	
	public TransactionDataElement(Long clientId, TransactionType type, String creationTime, Double currentBalance, Double amount, Currency currency) {
		this.clientId = clientId;
		this.transactionType = type.val();
		this.creationTime = creationTime;
		this.currentBalance = currentBalance;
		this.amount = amount;
		this.creationTime = DefaultDateFormatter.format(new Date());
		this.currency = currency.toString();
	}
	
}
