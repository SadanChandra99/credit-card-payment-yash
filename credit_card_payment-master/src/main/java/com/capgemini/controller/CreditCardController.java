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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.payloads.ApiResponse;
import com.capgemini.payloads.CreditCardRecieve;
import com.capgemini.payloads.CreditCardResponse;
import com.capgemini.service.CreditCardService;

@RestController
@RequestMapping("/creditcard")
public class CreditCardController {
	
	@Autowired
	private CreditCardService creditcardservice;
	
	@PostMapping("/insert")
	public ResponseEntity<CreditCardResponse> saveCreditCard(@Valid @RequestBody CreditCardRecieve creditcardreceive){
		CreditCardResponse savecreditcard = this.creditcardservice.insert(creditcardreceive);
		return new ResponseEntity<CreditCardResponse>(savecreditcard, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CreditCardResponse> updateCreditCard(@Valid @RequestBody CreditCardRecieve creditcardreceive, @PathVariable("id") long id ){
		CreditCardResponse updatecreditcard = this.creditcardservice.update(creditcardreceive, id);
		return new ResponseEntity<CreditCardResponse>(updatecreditcard, HttpStatus.CREATED);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<CreditCardResponse>> getCreditCards(){
		List<CreditCardResponse> allcreditcards = this.creditcardservice.getAll();
		return ResponseEntity.ok(allcreditcards);
	}
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<CreditCardResponse> getCreditCard(@PathVariable("id") long id){
		CreditCardResponse getcreditcard = this.creditcardservice.getById(id);
		return ResponseEntity.ok(getcreditcard);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteCreditCard(@PathVariable("id") long id){
		this.creditcardservice.delete(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted SucceFully !!", true), HttpStatus.OK);
	}
	
	
}