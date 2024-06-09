/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager;

import com.nbagenmanager.spursmanager.model.Contract;
import com.nbagenmanager.spursmanager.service.ContractService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ryan Chin
 */

//Controller class to connect to frontend
@RestController
@RequestMapping("/contract")
public class ContractResource {
    private final ContractService contractService;
    
    //The logic of operations can be found in the service class
    public ContractResource(ContractService contractService) {
        this.contractService = contractService;
    }
    
    //API endpoint
    @GetMapping("/all")
    public ResponseEntity<List<Contract>> getAllInjured(){
        List<Contract> contract = contractService.findAllContracts();
        return new ResponseEntity<>(contract, HttpStatus.OK);
    }
    
    @PostMapping("/enqueue")
    public ResponseEntity<Contract> enqueueContract(@RequestBody Contract contract){
        Contract contractExt = contractService.enqueueContract(contract);
        return new ResponseEntity<>(contractExt, HttpStatus.CREATED);
    }
    
    @GetMapping("/dequeue")
    public ResponseEntity<Contract> dequeueContract(){
        Contract contract = contractService.dequeueContract();
        return new ResponseEntity<>(contract, HttpStatus.OK);
    }
}
