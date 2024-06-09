/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager;

import com.nbagenmanager.spursmanager.model.InjuryReserve;
import com.nbagenmanager.spursmanager.service.InjuryReserveService;
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
@RequestMapping("/injury")
public class InjuryReserveResource {
    private final InjuryReserveService injuryReserveService;
    
    //The logic of operations can be found in the service class
    public InjuryReserveResource(InjuryReserveService injuryReserveService) {
        this.injuryReserveService = injuryReserveService;
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<InjuryReserve>> getAllInjured(){
        List<InjuryReserve> injured = injuryReserveService.findAllInjured();
        return new ResponseEntity<>(injured, HttpStatus.OK);
    }
    
    @PostMapping("/push")
    public ResponseEntity<InjuryReserve> pushInjured(@RequestBody InjuryReserve injured){
        InjuryReserve injuredReserve = injuryReserveService.pushInjured(injured);
        return new ResponseEntity<>(injuredReserve, HttpStatus.CREATED);
    }
    
    @GetMapping("/pop")
    public ResponseEntity<InjuryReserve> popInjured(){
        InjuryReserve injuredReserve = injuryReserveService.popInjured();
        return new ResponseEntity<>(injuredReserve, HttpStatus.OK);
    }
}
