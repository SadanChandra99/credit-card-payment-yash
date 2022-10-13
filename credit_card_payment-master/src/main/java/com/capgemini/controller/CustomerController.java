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

import com.capgemini.entity.Customer;
import com.capgemini.payloads.ApiResponse;
import com.capgemini.payloads.CustomerModel;
import com.capgemini.payloads.UserModel;
import com.capgemini.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/customers")
	public  ResponseEntity<List<CustomerModel>> listCustomers() {
		List<CustomerModel> allCustomers = this.customerService.getCustomers();
		return ResponseEntity.ok(allCustomers);
	}
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<CustomerModel> getCustomer(@PathVariable("id") long id){
		CustomerModel customerModel = this.customerService.getCustomer(id);
		return ResponseEntity.ok(customerModel);
	}
	
	@PostMapping("/save")
	public ResponseEntity<CustomerModel>  saveCustomer(@Valid @RequestBody CustomerModel customerModel){
		CustomerModel saveCustomer = this.customerService.saveCustomer(customerModel);
		return new ResponseEntity<CustomerModel>(saveCustomer, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomerModel>  updateCustomer(@Valid @RequestBody CustomerModel customerModel, long id){
		CustomerModel saveCustomer = this.customerService.updateCustomer(customerModel, id);
		return new ResponseEntity<CustomerModel>(saveCustomer, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable("id") long id){
		this.customerService.deleteCustomer(id);
	}
	
}
