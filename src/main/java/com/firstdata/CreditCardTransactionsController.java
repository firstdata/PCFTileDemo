package com.firstdata;

import com.firstdata.Form;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;

import com.firstdata.payeezy.PayeezyClientHelper;
import com.firstdata.payeezy.models.transaction.PayeezyResponse;
import com.firstdata.payeezy.models.transaction.TransactionRequest;

@Controller
public class CreditCardTransactionsController {

	private static Logger logger = Logger
			.getLogger(CreditCardTransactionsController.class);

	@Autowired
	PayeezyClientHelper payeezyClientHelper;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showForm(Model model) {
		model.addAttribute("form", new Form());
		model.addAttribute("responseMessage", "Enter data and hit Submit");
		return "form";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String submitForm(Form form, Model model) {
		logger.info("form: " + form.toString());

		TransactionRequest request = form.toTransactionRequest();
		String responseMessage = null;

		if (form.getTransactionType().equalsIgnoreCase("VOID")) {
			// Void Transaction
			try {
				
				// do primary one first
				PayeezyResponse primResp = payeezyClientHelper.doPrimaryTransaction(request);

				
				PayeezyResponse resp = payeezyClientHelper.doSecondaryTransaction("123", request);
				responseMessage = resp.getResponseBody();
				
			} catch (HttpClientErrorException hcee) {
				
				logger.error("Http Client error exception happened: " + hcee.getMessage() + " response body: " + hcee.getResponseBodyAsString() + " status text: " + hcee.getStatusText(), hcee);
				
				responseMessage = hcee.getResponseBodyAsString() + " : " + hcee.getStatusText();
				
				
			} catch (Exception e) {
				responseMessage = "Invalid Data for a VOID Transaction";
				logger.error("Invalid data for voidTransaction. Form: "
						+ form.toString() + " Error is: " + e.getMessage(), e);
			} finally {
				model.addAttribute("responseMessage", responseMessage);
				return "form";
			}

		} else {
			// Primary transaction
			try {
				PayeezyResponse resp = payeezyClientHelper.doPrimaryTransaction(request);
				responseMessage = resp.getResponseBody();
				
			} catch (HttpClientErrorException hcee) {
				
				logger.error("Http Client error exception happened: " + hcee.getMessage() + " response body: " + hcee.getResponseBodyAsString() + " status text: " + hcee.getStatusText(), hcee);
				
				responseMessage = hcee.getResponseBodyAsString() + " : " + hcee.getStatusText();
				
			} catch (Exception e) {
				responseMessage = "Invalid Data for AUTHORIZE / PURCHASE Transaction";
				logger.error("Error occurred: " + e.getMessage(), e);
			} finally {
				model.addAttribute("responseMessage", responseMessage);
				return "form";
			}
		}
	}


}
