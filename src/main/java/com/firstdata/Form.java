package com.firstdata;

import com.firstdata.payeezy.models.transaction.Address;
import com.firstdata.payeezy.models.transaction.Card;
import com.firstdata.payeezy.models.transaction.TransactionRequest;
import com.firstdata.payeezy.models.transaction.TransactionType;

public class Form {
	
	private String name;
	private String currency;
	private String amount;
	private String type;
	private String number;
	private String expiryDt;
	private String cvv;
	private String transactionType;

	private String street;
	private String city;
	private String state;
	private String country;
	private String zip;

	public TransactionRequest toTransactionRequest() {

		TransactionRequest request = new TransactionRequest();
		request.setAmount(this.getAmount());
		request.setCurrency(this.getCurrency());
		request.setPaymentMethod("credit_card");
		if (this.getTransactionType().equalsIgnoreCase("PURCHASE")) {
			request.setTransactionType(TransactionType.PURCHASE.name());
		} else {
			request.setTransactionType(TransactionType.AUTHORIZE.name());
		}
		Card card = new Card();
		card.setCvv(this.getCvv());
		card.setExpiryDt(this.getExpiryDt());
		card.setName(this.getName());
		card.setType(this.getType().toLowerCase());		
		card.setNumber(this.getNumber());
		request.setCard(card);
		Address address = new Address();
		request.setBilling(address);
		address.setState(this.getState());
		address.setAddressLine1(this.getStreet());
		address.setZip(this.getZip());
		address.setCountry(this.getCountry());
		return request;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getExpiryDt() {
		return expiryDt;
	}

	public void setExpiryDt(String expiryDt) {
		this.expiryDt = expiryDt;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "TransactionFormInput [name=" + name + ", currency=" + currency
				+ ", amount=" + amount + ", type=" + type + ", number="
				+ number + ", expiryDt=" + expiryDt + ", cvv=" + cvv
				+ ", transactionType=" + transactionType + ", street=" + street
				+ ", city=" + city + ", state=" + state + ", country="
				+ country + ", zip=" + zip + "]";
	}

}
