package com.capgemini.service;


import org.springframework.stereotype.Service;

import com.capgemini.entity.Payment;

@Service
public interface PaymentService {
	Payment addPayment(Payment payment);

	void removePayment(long paymentId);

	Payment updatePayment(long paymentId, Payment payment);

	Payment getPayment(long paymentId);

}
