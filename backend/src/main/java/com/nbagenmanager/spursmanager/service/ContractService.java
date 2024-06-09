/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager.service;

import com.nbagenmanager.spursmanager.model.Contract;
import com.nbagenmanager.spursmanager.repo.ContractRepo;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryan Chin
 */
@Service
public class ContractService {
    private final ContractRepo contractRepo;

    public ContractService(ContractRepo contractRepo) {
        this.contractRepo = contractRepo;
    }
    
    //Return list of the contract queue. They all ordered by their sequence in the queue
    public List<Contract> findAllContracts(){
        return this.contractRepo.findAllByOrderBySeqAsc();
    }
    
    //Enqueue a player into the queue
    public Contract enqueueContract(Contract player){
        //Priority queue is used to give priority for higher point players
        Queue<Contract> contractExt = new PriorityQueue<>((c1, c2) -> Double.compare(c2.getPoints(), c1.getPoints())); 
        //Take all players out from database and put into the queue
        for (Contract contract : contractRepo.findAll()){
            contractExt.offer(contract);
        }
        contractExt.offer(player); //Enqueue the new player
        
        contractRepo.deleteAll(); //Clear the database
        //Store the new queue into the database
        int i=0;
        while(!contractExt.isEmpty()){
            Contract addContract = contractExt.poll();
            addContract.setSeq((long)i++);
            contractRepo.save(addContract);
        }
        return player;
    }
    
    //Dequeue a player from the queue
    public Contract dequeueContract(){
        Queue<Contract> contractExt = new PriorityQueue<>((c1, c2) -> Double.compare(c2.getPoints(), c1.getPoints())); //Create the priority queue based on points
        //Take all players out from database and put into the queue
        for (Contract contract : contractRepo.findAll()){
            contractExt.offer(contract);
        }
        Contract temp = contractExt.poll(); //Dequeue the player
        
        contractRepo.deleteAll(); //Clear the database
        //Store the new queue into the database
        int i=0;
        while(!contractExt.isEmpty()){
            Contract addContract = contractExt.poll();
            addContract.setSeq((long)i++);
            contractRepo.save(addContract);
        }
        
        return temp;
    }
}
