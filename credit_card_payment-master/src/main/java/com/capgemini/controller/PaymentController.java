package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entity.Payment;
import com.capgemini.service.PaymentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	@Autowired
	private PaymentService repo1;

	@PostMapping("/credits/add")
	public Payment addPayment(@RequestBody Payment payment) {
		Payment add1 = repo1.addPayment(payment);
		return add1;
	}

	@PutMapping("/credits/update/{no}")
	public Payment updatePayment(@PathVariable(value = "no") long paymentId, @RequestBody Payment payment) {
		Payment p = repo1.getPayment(paymentId);
		p.setAmountDue(payment.getAmountDue());
		p.setMethod(payment.getMethod());
		p.setPaymentId(payment.getPaymentId());
		return repo1.updatePayment(paymentId, p);
	}

	@DeleteMapping("/credits/delete/{no}")
	public String removePayment(@PathVariable(value = "no") long paymentId) {
		repo1.removePayment(paymentId);
		return "Deletion is successfull";
	}

	@GetMapping("/credits/{no}")
	public Payment getPayment(@PathVariable(value = "no") long paymentId) {
		Payment p2 = repo1.getPayment(paymentId);
		return p2;
	}

}