package com.capgemini.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.customexception.ResourseNotFoundException;

import com.capgemini.entity.Customer;
import com.capgemini.entity.Transaction;

import com.capgemini.payloads.TransactionRecieve;
import com.capgemini.payloads.TransactionResponse;
import com.capgemini.repo.CustomerRepo;
import com.capgemini.repo.TransactionRepo;
import com.capgemini.service.TransactionService;
@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepo transectionRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TransactionResponse insert(TransactionRecieve transectionRecieve) {
		// TODO Auto-generated method stub
		      Transaction transection = this.modelMapper.map(transectionRecieve, Transaction.class);
				Customer customer = this.customerRepo.findById(transectionRecieve.getCustomerId()).orElseThrow(
						() -> new ResourseNotFoundException("Customer", "customer id", transectionRecieve.getCustomerId()));
				transection.setCustomer(customer);
				Transaction save = this.transectionRepo.save(transection);
				TransactionResponse response = this.modelMapper.map(save, TransactionResponse.class);
				return response;
	}

	@Override
	public TransactionResponse update(TransactionRecieve transectionRecieve, long id) {
		// TODO Auto-generated method stub
		Transaction transection = this.transectionRepo.findById(id)
				.orElseThrow(() -> new ResourseNotFoundException("Transection", "transection-id", id));
		transection.setTranId(transectionRecieve.getTranId());
		transection.setAmount(transectionRecieve.getAmount());
		transection.setCardNumber(transectionRecieve.getCardNumber());
		transection.setPaymentMethod(transectionRecieve.getPaymentMethod());
		transection.setDescription(transectionRecieve.getDescription());
		transection.setStatus(transectionRecieve.getStatus());
		transection.setTranDate(transectionRecieve.getTranDate());
		Customer customer = this.customerRepo.findById(transectionRecieve.getCustomerId()).orElseThrow(
				() -> new ResourseNotFoundException("Customer", "customer id", transectionRecieve.getCustomerId()));
		transection.setCustomer(customer);
		Transaction save = this.transectionRepo.save(transection);
		TransactionResponse response = this.modelMapper.map(save, TransactionResponse.class);
		return response;
	
	}

	@Override
	public List<TransactionResponse> getAll() {
		// TODO Auto-generated method stub
		List<Transaction> transections = this.transectionRepo.findAll();
		List<TransactionResponse> response = transections.stream()
				.map((transection) -> this.modelMapper.map(transection, TransactionResponse.class)).collect(Collectors.toList());
		return response;
	}

	@Override
	public TransactionResponse getById(long id) {
		// TODO Auto-generated method stub
		Transaction transection = this.transectionRepo.findById(id)
				.orElseThrow(() -> new ResourseNotFoundException("Transection", "transection-id", id));
		TransactionResponse response = this.modelMapper.map(transection, TransactionResponse.class);
		return response;
	
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		Transaction transection = this.transectionRepo.findById(id)
				.orElseThrow(() -> new ResourseNotFoundException("Transection", "transection-id", id));
      this.transectionRepo.delete(transection);
	}
}
