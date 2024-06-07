/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager.service;

import com.nbagenmanager.spursmanager.model.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Ryan Chin
 */
@Service
public class FreeAgentService {
    
    private PlayerService playerService;
    
    @Autowired
    public FreeAgentService(PlayerService playerService) {
        this.playerService = playerService;
    }
    
    //Function to get list of all free agents using external API
    public List<Player> getFreeAgents() throws ParseException{
        List<Player> list = new ArrayList<>();
        //Connect to external API
        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", "6a3181ee-21fa-4e29-8d0c-5655e5fd0f3b"); //Authorization key to access API
        HttpEntity<Void> requestEntity = new HttpEntity<>(header);
        JSONParser parser = new JSONParser();
        
        String urlPlayers = "https://api.balldontlie.io/v1/players/?per_page=100&cursor={cursor}"; //External API request link
        JSONArray playerData = new JSONArray(); //The data will be returned in a JSON format
        //Need to send multiple requests to get all needed players
        for (int i=0; i<500; i+=100){
            RestTemplate restTemplate1 = new RestTemplate();
            ResponseEntity<String> playerResponse = restTemplate1.exchange(urlPlayers, HttpMethod.GET, requestEntity, String.class, i);
            JSONObject jsonObj = (JSONObject) parser.parse(playerResponse.getBody());
            //The API returns in a single array called "data"
            JSONArray data = (JSONArray) jsonObj.get("data");
            for (Object element : data){
                playerData.add((JSONObject) element);
            }
        }
        
        //API request to get the stats 
        String urlStats = "https://api.balldontlie.io/v1/season_averages/?season=2023";
        //As the API used is the free service, we need to manually append custom search to get the player stats
        for (int i=0; i<500; i++){
            urlStats = urlStats + "&player_ids[]=" + i;
        }
        RestTemplate restTemplate2 = new RestTemplate();
        ResponseEntity<String> statResponse = restTemplate2.exchange(urlStats, HttpMethod.GET, requestEntity, String.class);
        JSONObject jsonObj = (JSONObject) parser.parse(statResponse.getBody());
        JSONArray data = (JSONArray) jsonObj.get("data");
        //Create new player and append into list for each data element obtained. This is done by combining the first player details and the player stats
        for (Object player : data){
            JSONObject playerStats = (JSONObject) player;
            JSONObject playerDetails = (JSONObject) playerData.get(((Number)playerStats.get("player_id")).intValue()-1);
            
            String name = (String)playerDetails.get("first_name")+ " " + (String) playerDetails.get("last_name"); //Combine first name and last name
            int age = playerDetails.get("draft_year")==null ? 0 : 2024 - ((Number)playerDetails.get("draft_year")).intValue() + 20; //There is no info on age, so we estimate age through their draft year
            double height = playerDetails.get("height")==null ? 0 : Double.parseDouble(((String)playerDetails.get("height")).substring(0,1)) * 0.3048 + (Double.parseDouble(((String)playerDetails.get("height")).substring(2))*0.0254);
            height = Math.floor(height*100)/100; //Remove floating point error
            double weight = playerDetails.get("weight")==null ? 0 : Double.parseDouble(((String)playerDetails.get("weight")))* 0.45359237;
            weight = Math.floor(weight*100)/100;
            
            String position = (String)playerDetails.get("position");
            double points = ((Number)playerStats.get("pts")).doubleValue();
            double rebound = ((Number)playerStats.get("reb")).doubleValue();
            double assists = ((Number)playerStats.get("ast")).doubleValue();
            double steals = ((Number)playerStats.get("stl")).doubleValue();
            double blocks = ((Number)playerStats.get("blk")).doubleValue();
            double salary = 1000 + (points*100); //No info on salary. Calculate salary based on points, taking to account superstar players
            salary = Math.floor(salary*100)/100;
            
            //Add the player to list
            list.add(new Player((Long) playerDetails.get("id"), name, age, height, weight, position, salary,points,rebound,assists,steals,blocks,0,0));
        }
        
        //Remove players that are already recruited into the roster
        for (Player player : playerService.findAllPlayers()){
            list.removeIf(obj -> Objects.equals(obj.getId(), player.getId()));
        }
        
        return list;
    }
}
