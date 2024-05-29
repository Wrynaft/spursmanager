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
    
    public List<Contract> findAllContracts(){
        return this.contractRepo.findAllByOrderBySeqAsc();
    }
    
    public Contract enqueueContract(Contract player){
        Queue<Contract> contractExt = new PriorityQueue<>((c1, c2) -> Double.compare(c2.getPoints(), c1.getPoints()));
        for (Contract contract : contractRepo.findAll()){
            contractExt.offer(contract);
        }
        contractExt.offer(player);
        
        contractRepo.deleteAll();
        int i=0;
        while(!contractExt.isEmpty()){
            Contract addContract = contractExt.poll();
            addContract.setSeq((long)i++);
            contractRepo.save(addContract);
        }
        return player;
    }
    
    public Contract dequeueContract(){
        Queue<Contract> contractExt = new PriorityQueue<>((c1, c2) -> Double.compare(c2.getPoints(), c1.getPoints()));
        for (Contract contract : contractRepo.findAll()){
            contractExt.offer(contract);
        }
        Contract temp = contractExt.poll();
        
        contractRepo.deleteAll();
        int i=0;
        while(!contractExt.isEmpty()){
            Contract addContract = contractExt.poll();
            addContract.setSeq((long)i++);
            contractRepo.save(addContract);
        }
        
        return temp;
    }
}
