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
    
    //Returns the stack of injured players. They will be sorted by sequence, which indicates their position in the stack.
    public List<InjuryReserve> findAllInjured(){
        return this.injuryReserveRepo.findAllByOrderBySeqDesc();
    }
    
    //Push a player into the injured stack
    public InjuryReserve pushInjured(InjuryReserve injured){
        Stack<InjuryReserve> stack = new Stack<>(); //Create the stack
        long i=0;
        for (InjuryReserve initInjured : injuryReserveRepo.findAllByOrderBySeqAsc()){
            initInjured.setSeq(i++); //As they are taken out of database, set their sequence for when they get stored back later
            stack.push(initInjured); //Get the player out from database and push it into the stack
        }
        injuryReserveRepo.deleteAll(); //Every player is taken out from the database and stored into the stack. The database is then cleared. 
        //When the operations with stack are done, we will store the whole stack back into the database later
        
        injured.setSeq((long) stack.size());
        InjuryReserve temp = stack.push(injured); //Push the player into the stack
        while (!stack.empty()){
            injuryReserveRepo.save(stack.pop()); //Empty the stack and store it into the database
        }
        return temp;
    }
    
    //Pop a player from injured stack
    public InjuryReserve popInjured(){
        Stack<InjuryReserve> stack = new Stack<>(); //Create the stack
        long i=0;
        //Take the players out from the database and store into stack, setting their sequence
        for (InjuryReserve initInjured : injuryReserveRepo.findAllByOrderBySeqAsc()){
            initInjured.setSeq(i++);
            stack.push(initInjured);
        }
        injuryReserveRepo.deleteAll(); //Clear the database
        
        InjuryReserve recovered = stack.pop(); //Pop the player out
        while (!stack.empty()){
            injuryReserveRepo.save(stack.pop()); //Store the remaining stack into database
        }
        
        return recovered; //Return the popped player
    }
}
