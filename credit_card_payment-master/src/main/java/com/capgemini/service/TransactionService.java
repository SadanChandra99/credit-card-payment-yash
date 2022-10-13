package com.capgemini.service;

import java.util.List;

import com.capgemini.payloads.TransactionRecieve;
import com.capgemini.payloads.TransactionResponse;

public interface TransactionService {

	public TransactionResponse insert(TransactionRecieve transectionRecieve);
	public TransactionResponse update(TransactionRecieve transectionRecieve, long id);
	public List<TransactionResponse> getAll();
	public TransactionResponse getById(long id);
	public void delete(long id);
}
