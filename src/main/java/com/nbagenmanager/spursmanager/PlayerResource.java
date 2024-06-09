/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager;

import com.nbagenmanager.spursmanager.model.Player;
import com.nbagenmanager.spursmanager.model.PlayerRanking;
import com.nbagenmanager.spursmanager.service.InvalidSearchParameterException;
import com.nbagenmanager.spursmanager.service.PlayerService;
import com.nbagenmanager.spursmanager.service.RankingService;

import java.util.List;
import java.util.Map;
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

/**
 *
 * @author Ryan Chin
 */
@RestController
@RequestMapping("/players") //Endpoint for API access from front-end
public class PlayerResource {
    private final PlayerService playerService; //Player service class needed for operations related to Player
    private final RankingService rankingService; //Ranking service class needed for operations related to Ranking
    
    public PlayerResource(PlayerService playerService, RankingService rankingService) {
        this.playerService = playerService;
        this.rankingService = rankingService;
    }
    
    //Returns list of players in the roster
    @GetMapping("/all")
    public ResponseEntity<List<Player>> getAllPlayer(){
        List<Player> players = playerService.findAllPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }
    
    //Finds a particular player by their ID
    @GetMapping("/find/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("id") Long id){
        Player player = playerService.findPlayerById(id);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }
    
    //Validate roster based on rules and requirements. Refer PlayerService class for details
    @GetMapping("/validate")
    public ResponseEntity<String> validateRoster(){
        if (playerService.validateRoster())
            return new ResponseEntity<String>(HttpStatus.OK);
        else
            return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
    }
    
    //Return size of roster
    @GetMapping("/size")
    public ResponseEntity<Integer> getSize(){
        Integer size = playerService.getSize();
        return new ResponseEntity<>(size, HttpStatus.OK);
    }
    
    //Return total salary of roster
    @GetMapping("/salary")
    public ResponseEntity<Double> getSalary(){
        Double salary = playerService.getSalary();
        return new ResponseEntity<>(salary, HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Player>> searchPlayers(@RequestBody Map<String, String> searchParams) {
        try {
            List<Player> filteredPlayers = playerService.searchPlayers(searchParams);

            if (filteredPlayers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(filteredPlayers, HttpStatus.OK);
        } catch (InvalidSearchParameterException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<PlayerRanking>> getRankedPlayers() {
        List<Player> players = playerService.findAllPlayers();
        List<PlayerRanking> rankedPlayers = rankingService.rankPlayers(players);
        return new ResponseEntity<>(rankedPlayers, HttpStatus.OK);
    }

    //Add a new player into roster
    @PostMapping("/add")
    public ResponseEntity<Player> addPlayer(@RequestBody Player player){
        Player newPlayer = playerService.addPlayer(player);
        return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
    }
    
    //Search for player based on attributes passed from front end
    // @PostMapping("/search")
    // public ResponseEntity<List<Player>> searchPlayers(@RequestBody Map<String, String> searchParams){
    //     return new ResponseEntity<>(null, HttpStatus.OK);
    // }


    
    //Update an existing player
    @PutMapping("/update")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player){
        Player updatePlayer = playerService.updatePlayer(player);
        return new ResponseEntity<>(updatePlayer, HttpStatus.CREATED);
    }
    
    //Delete a player by their ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Player> deletePlayer(@PathVariable("id") Long id){
        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
