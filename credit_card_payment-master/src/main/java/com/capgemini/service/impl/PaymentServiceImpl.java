package com.capgemini.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entity.Payment;
import com.capgemini.repo.PaymentRepo;
import com.capgemini.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentRepo repo;

	public PaymentServiceImpl(PaymentRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Payment addPayment(Payment payment) {
		Payment add = this.repo.save(payment);
		return add;
	}

	@Override
	public void removePayment(long paymentId) {
		repo.deleteById(paymentId);

	}

	@Override
	public Payment updatePayment(long paymentId, Payment payment) {
		return repo.save(payment);
	}

	@Override
	public Payment getPayment(long paymentId) {
		Payment get = repo.findById(paymentId).get();
		return get;
	}

}