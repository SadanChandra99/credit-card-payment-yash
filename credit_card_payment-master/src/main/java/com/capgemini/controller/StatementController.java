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

import com.capgemini.payloads.StatementRecieve;
import com.capgemini.payloads.StatementResponse;
import com.capgemini.service.StatementService;

@RestController
@RequestMapping("/statement")
public class StatementController {
	
	@Autowired
	StatementService statementService;
	
	@GetMapping("/statements")
	public  ResponseEntity<List<StatementResponse>> listStatements() {
		List<StatementResponse> allStatements = this.statementService.getAll();
		return ResponseEntity.ok(allStatements);
	}
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<StatementResponse> getStatement(@PathVariable("id") long id){
		StatementResponse statementResponse = this.statementService.getById(id);
		return ResponseEntity.ok(statementResponse);
	}
	
	@PostMapping("/save")
	public ResponseEntity<StatementResponse>  saveStatement(@Valid @RequestBody StatementRecieve statementRecieve){
		StatementResponse saveStatement = this.statementService.insert(statementRecieve);
		return new ResponseEntity<StatementResponse>(saveStatement, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<StatementResponse> updateStatement(
			@Valid @RequestBody StatementRecieve statementRecieve, long id) {
		StatementResponse statementResponse = this.statementService.update(statementRecieve, id);
		return new ResponseEntity<StatementResponse>(statementResponse, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteStatement(@PathVariable("id") long id) {
		this.statementService.delete(id);
	}

}
