package com.capgemini.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.payloads.TransactionRecieve;
import com.capgemini.payloads.TransactionResponse;
import com.capgemini.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	TransactionService transectionService;

	@GetMapping("/transactions")
	public ResponseEntity<List<TransactionResponse>> listTransections() {
		List<TransactionResponse> allTransections = this.transectionService.getAll();
		return ResponseEntity.ok(allTransections);
	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<TransactionResponse> getTransaction(@PathVariable("id") long id) {
		TransactionResponse transectionResponse = this.transectionService.getById(id);
		return ResponseEntity.ok(transectionResponse);
	}

	@PostMapping("/save")
	public ResponseEntity<TransactionResponse> saveTransaction(
			@Valid @RequestBody TransactionRecieve transectionRecieve) {
		TransactionResponse savetransaction = this.transectionService.insert(transectionRecieve);
		return new ResponseEntity<TransactionResponse>(savetransaction, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TransactionResponse> updateTransaction(
			@Valid @RequestBody TransactionRecieve transectionRecieve, long id) {
		TransactionResponse savetrTransection = this.transectionService.update(transectionRecieve, id);
		return new ResponseEntity<TransactionResponse>(savetrTransection, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteTransaction(@PathVariable("id") long id) {
		this.transectionService.delete(id);
	}

}
