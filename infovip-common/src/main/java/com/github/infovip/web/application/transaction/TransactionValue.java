package com.github.infovip.web.application.transaction;

import com.github.infovip.core.es.type.Currency;

import lombok.Data;

@Data
public class TransactionValue {

	private Double price;
	
	private Currency currency;
	
	public TransactionValue() {
		// TODO Auto-generated constructor stub
	}
	
	public TransactionValue(Currency currency, Double price) {
		this.currency = currency;
		this.price = price;
	}

	public static TransactionValue create( Currency currency, Double price ) {
		return new TransactionValue( currency , price );
	}

	
}
