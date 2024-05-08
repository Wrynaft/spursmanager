/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager.service;

import com.nbagenmanager.spursmanager.model.Player;
import com.nbagenmanager.spursmanager.repo.PlayerRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryan Chin
 */
@Service
public class PlayerService {
    private final PlayerRepo playerRepo;

    @Autowired
    public PlayerService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }
    
    public Player addPlayer(Player player){
        if (playerRepo.findAll().size()>=15){
            throw new ExceedTeamSizeException("Adding this player will exceed the maximum team size!");
        }
        double currentSalary=0;
        for (Player players : playerRepo.findAll()){
            currentSalary += players.getSalary();
        }
        if (currentSalary + player.getSalary() > 20000){
            throw new ExceedSalaryException("Adding this player will exceed the salary cap!");
        }
        return playerRepo.save(player);
    }
    
    public List<Player> findAllPlayers(){
        return playerRepo.findAll();
    }
    
    public Player updatePlayer(Player player){
        return playerRepo.save(player);
    }
    
    public Player findPlayerById(Long id){
        return playerRepo.findPlayerById(id).orElseThrow(()-> new PlayerNotFoundException("User was not found"));
    }
    
    public void deletePlayer(Long id){
        playerRepo.deletePlayerById(id);
    }
}
