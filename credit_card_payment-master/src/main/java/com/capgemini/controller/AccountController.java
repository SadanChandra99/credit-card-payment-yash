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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.payloads.AccountRecieve;
import com.capgemini.payloads.AccountResponse;
import com.capgemini.payloads.AddressRecieve;
import com.capgemini.payloads.AddressResponse;
import com.capgemini.payloads.TransactionRecieve;
import com.capgemini.payloads.TransactionResponse;
import com.capgemini.service.AccountService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping("/accounts")
	public ResponseEntity<List<AccountResponse>> listAccounts() {
		List<AccountResponse> allAccounts = this.accountService.getAll();
		return ResponseEntity.ok(allAccounts);
	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<AccountResponse> getAccount(@PathVariable("id") long id) {
		AccountResponse accountResponse = this.accountService.getById(id);
		return ResponseEntity.ok(accountResponse);
	}

	@PostMapping("/save")
	public ResponseEntity<AccountResponse> saveAccount(@Valid @RequestBody AccountRecieve accountRecieve) {
		AccountResponse response = this.accountService.insert(accountRecieve);
		return new ResponseEntity<AccountResponse>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AccountResponse> updateAccount(@Valid @RequestBody AccountRecieve accountRecieve, long id) {
		AccountResponse accountResponse = this.accountService.update(accountRecieve, id);
		return new ResponseEntity<AccountResponse>(accountResponse, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteAccount(@PathVariable("id") long id) {
		this.accountService.delete(id);
	}

}
