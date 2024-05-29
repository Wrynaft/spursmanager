/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager.service;

import com.nbagenmanager.spursmanager.model.InjuryReserve;
import com.nbagenmanager.spursmanager.repo.InjuryReserveRepo;
import java.util.List;
import java.util.Stack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryan Chin
 */
@Service
public class InjuryReserveService {
    private final InjuryReserveRepo injuryReserveRepo;
    
    @Autowired
    public InjuryReserveService(InjuryReserveRepo injuryReserveRepo) {
        this.injuryReserveRepo = injuryReserveRepo;
    }
    
    public List<InjuryReserve> findAllInjured(){
        return this.injuryReserveRepo.findAllByOrderBySeqDesc();
    }
    
    public InjuryReserve pushInjured(InjuryReserve injured){
        Stack<InjuryReserve> stack = new Stack<>();
        long i=0;
        for (InjuryReserve initInjured : injuryReserveRepo.findAllByOrderBySeqAsc()){
            initInjured.setSeq(i++);
            stack.push(initInjured);
        }
        injuryReserveRepo.deleteAll();
        
        injured.setSeq((long) stack.size());
        InjuryReserve temp = stack.push(injured);
        while (!stack.empty()){
            injuryReserveRepo.save(stack.pop());
        }
        return temp;
    }
    
    public InjuryReserve popInjured(){
        Stack<InjuryReserve> stack = new Stack<>();
        long i=0;
        for (InjuryReserve initInjured : injuryReserveRepo.findAllByOrderBySeqAsc()){
            initInjured.setSeq(i++);
            stack.push(initInjured);
        }
        injuryReserveRepo.deleteAll();
        
        InjuryReserve recovered = stack.pop();
        while (!stack.empty()){
            injuryReserveRepo.save(stack.pop());
        }
        
        return recovered;
    }
}
