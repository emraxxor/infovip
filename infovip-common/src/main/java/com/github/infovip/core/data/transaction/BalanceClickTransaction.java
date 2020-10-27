package com.github.infovip.core.data.transaction;

import com.github.infovip.core.data.TransactionDataElement;
import com.github.infovip.core.data.type.TransactionType;
import com.github.infovip.core.date.DefaultDateFormatter;
import com.github.infovip.core.es.type.Currency;
import com.github.infovip.core.es.type.DefaultDocumentType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BalanceClickTransaction extends TransactionDataElement {

	private Long webshopId;
	
	private Long sourceId;
	
	private String remoteIP;
	
	private String remoteHost;
	
	private String browser;
	
	public BalanceClickTransaction(
			Long companyId, 
			TransactionType type, 
			String creationTime, 
			Double currentBalance,
			Double amount,
			Currency currency
		) {
		super(companyId, type, creationTime, currentBalance, amount,currency);
	}
	
	
	public BalanceClickTransaction(
			DefaultDocumentType ddt, 
			TransactionType type, 
			Double amount, 
			Double currentBalance,
			String remoteIp,
			String remoteHost,
			String browser,
			Currency currency
			) {
		this(ddt.getCompanyId(), type , DefaultDateFormatter.current(), currentBalance, amount,currency);
		this.remoteIP = remoteIp;
		this.remoteHost = remoteHost;
		this.browser = browser;
	}

	
	
}
