package com.github.infovip.core.payment;

import com.github.infovip.configuration.DefaultWebAppConfiguration;
import com.github.infovip.core.elasticsearch.ESExtendedDataElement;
import com.github.infovip.core.elasticsearch.ESOperationType;

public class PaymentDataElement<T> implements ESExtendedDataElement<T> {

	private String documentId = null;
	
	private ESOperationType operation;
	
	private T data;
	
	public PaymentDataElement(ESOperationType operation) {
		super();
		this.operation = operation;
	}
	
	public PaymentDataElement(T data, ESOperationType operation) {
		this(operation);
		this.data = data;
	}
	
	public PaymentDataElement(T data, ESOperationType operation, String documentId) {
		this(operation);
		this.data = data;
		this.documentId = documentId;
	}
	
	@Override
	public String id() {
		return documentId;
	}

	@Override
	public String index() {
		return DefaultWebAppConfiguration.ESConfiguration.PAYMENT_INDEX;
	}

	@Override
	public String type() {
		return DefaultWebAppConfiguration.ESConfiguration.PAYMENT_DATA_TYPE;
	}

	@Override
	public ESOperationType operation() {
		return operation;
	}

	@Override
	public T data() {
		return data;
	}
	
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	
	public void setOperation(ESOperationType operation) {
		this.operation = operation;
	}

	@Override
	public ESExtendedDataElement<T> operationIndex() {
		this.operation = ESOperationType.INDEX;
		return this;
	}

	@Override
	public ESExtendedDataElement<T> operationUpdate() {
		this.operation = ESOperationType.UPDATE;
		return this;
	}

	@Override
	public ESExtendedDataElement<T> operationDelete() {
		this.operation = ESOperationType.DELETE;
		return this;
	}

}
