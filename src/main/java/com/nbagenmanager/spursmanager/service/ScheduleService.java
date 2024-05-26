/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager.service;

import com.nbagenmanager.spursmanager.model.City;
import com.nbagenmanager.spursmanager.model.Graph;
import com.nbagenmanager.spursmanager.model.Schedule;
import com.nbagenmanager.spursmanager.repo.ScheduleRepo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryan Chin
 */
@Service
public class ScheduleService {
    private final ScheduleRepo scheduleRepo;
    
    private Graph nbaGraph;
    private List<City> shortestRoundTripPathDFS;
    private int minTotalDistanceDFS; 

    @Autowired
    public ScheduleService(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
        initializeGraph();
    }
    
    private void initializeGraph() {
        // Initialize the graph with NBA cities and distances
        nbaGraph = new Graph();


        // Adding NBA cities
        City warriors = new City("Golden State Warriors");
        City nuggets = new City("Denver Nuggets");
        City lakers = new City("Los Angeles Lakers");
        City suns = new City("Phoenix Suns");
        City spurs = new City("San Antonio Spurs");
        City thunder = new City("Oklahoma City Thunder");
        City celtics = new City("Boston Celtics");
        City heat = new City("Miami Heat");
        City magic = new City("Orlando Magic");
        City rockets = new City("Houston Rockets");

        // Adding cities to the graph
        nbaGraph.addCity(warriors);
        nbaGraph.addCity(nuggets);
        nbaGraph.addCity(lakers);
        nbaGraph.addCity(suns);
        nbaGraph.addCity(spurs);
        nbaGraph.addCity(thunder);
        nbaGraph.addCity(celtics);
        nbaGraph.addCity(heat);
        nbaGraph.addCity(magic);
        nbaGraph.addCity(rockets);

        // Adding edges between cities with distances
        nbaGraph.addEdgeBidirectional(warriors, nuggets, 1507);
        nbaGraph.addEdgeBidirectional(warriors, lakers, 554);
        nbaGraph.addEdgeBidirectional(lakers, suns, 577);
        nbaGraph.addEdgeBidirectional(suns, spurs, 500);
        nbaGraph.addEdgeBidirectional(spurs, thunder, 678);
        nbaGraph.addEdgeBidirectional(thunder, nuggets, 942);
        nbaGraph.addEdgeBidirectional(nuggets, celtics, 2845);
        nbaGraph.addEdgeBidirectional(celtics, heat, 3045);
        nbaGraph.addEdgeBidirectional(heat, magic, 268);
        nbaGraph.addEdgeBidirectional(magic, rockets, 458);
        nbaGraph.addEdgeBidirectional(rockets, thunder, 778);
        nbaGraph.addEdgeBidirectional(rockets, spurs, 983);
        nbaGraph.addEdgeBidirectional(lakers, thunder, 1901);
        nbaGraph.addEdgeBidirectional(warriors, thunder, 2214);
        nbaGraph.addEdgeBidirectional(magic, spurs, 1137);
        nbaGraph.addEdgeBidirectional(celtics, rockets, 1137);
        
        /////2 way////////////
        nbaGraph.addEdgeBidirectional(warriors, nuggets, 1507);
        nbaGraph.addEdgeBidirectional(lakers, warriors, 554);
        nbaGraph.addEdgeBidirectional(suns, lakers, 577);
        nbaGraph.addEdgeBidirectional(spurs, suns, 500);
        nbaGraph.addEdgeBidirectional(thunder, spurs, 678);
        nbaGraph.addEdgeBidirectional(nuggets, thunder, 942);
        nbaGraph.addEdgeBidirectional(thunder, nuggets, 2845);
        nbaGraph.addEdgeBidirectional(heat, celtics, 3045);
        nbaGraph.addEdgeBidirectional(magic, heat, 268);
        nbaGraph.addEdgeBidirectional(rockets, magic, 458);
        nbaGraph.addEdgeBidirectional(thunder, rockets, 778);
        nbaGraph.addEdgeBidirectional(spurs, rockets, 983);
        nbaGraph.addEdgeBidirectional(thunder, lakers, 1901);
        nbaGraph.addEdgeBidirectional(thunder, warriors, 2214);
        nbaGraph.addEdgeBidirectional(spurs, magic, 1137);
        nbaGraph.addEdgeBidirectional(rockets, celtics, 1137);
    }
    
    public List<Schedule> findShortestRoundTripPathDFS() {
        deleteAllSchedule();
        initializeGraph();
        minTotalDistanceDFS = Integer.MAX_VALUE; // Initialize minimum distance to maximum value
        shortestRoundTripPathDFS = new ArrayList<>();
    
        City startingCity = new City("San Antonio Spurs"); // Starting and ending at San Antonio Spurs
        Set<City> visited = new HashSet<>();
        List<City> path = new ArrayList<>();
        path.add(startingCity);
        visited.add(startingCity);
        dfs(startingCity, startingCity, visited, path, 0); // Pass starting city as both current and starting city

        // Create a map to hold the data you want to serialize
            Map<String, Object> result = new HashMap<>();
            result.put("shortestPath", shortestRoundTripPathDFS);
            result.put("minTotalDistance", minTotalDistanceDFS);

            List<Schedule> newSchedule = new ArrayList<>();
            for (Integer i=1; i<=shortestRoundTripPathDFS.size(); i++){
                Schedule schedule = new Schedule(i, shortestRoundTripPathDFS.get(i-1).getName());
                newSchedule.add(schedule);
            }

            return scheduleRepo.saveAll(newSchedule);
            // Serialize the map to JSON
    //        ObjectMapper objectMapper = new ObjectMapper();
    //        try {
    //            String json = objectMapper.writeValueAsString(result);
    //            System.out.println("JSON Object: " + json); // Print the JSON for testing
    //            // Now 'json' contains the JSON representation of your result
    //            // You can pass this JSON string to your team member for further processing
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
}
    
    private void dfs(City currentCity, City startingCity, Set<City> visited, List<City> path, int currentDistance) {
        System.out.println("Current city: " + currentCity.getName());
        System.out.println("Visited cities: " + visited);
        System.out.println("Current path: " + path);
        System.out.println("Current distance: " + currentDistance);

        if (visited.size() == nbaGraph.getAdjacencyList().size() && currentCity.equals(startingCity)) {
            if (currentDistance < minTotalDistanceDFS || minTotalDistanceDFS == Integer.MAX_VALUE) {
                minTotalDistanceDFS = currentDistance;
                shortestRoundTripPathDFS = new ArrayList<>(path);
            }
            System.out.println("Found a round-trip path: " + shortestRoundTripPathDFS);
            return; // Terminate the exploration
        }

        for (Map.Entry<City, Integer> entry : nbaGraph.getAdjacencyList().get(currentCity).entrySet()) {
            City nextCity = entry.getKey();
            int distance = entry.getValue();
            if (!visited.contains(nextCity) || (visited.contains(nextCity) && nextCity.equals(startingCity))) {
                visited.add(nextCity);
                path.add(nextCity);
                dfs(nextCity, startingCity, visited, path, currentDistance + distance);
                visited.remove(nextCity);
                path.remove(path.size() - 1);
            }
        }
    }
    
    public int shortestDistance(){
        return minTotalDistanceDFS;
    }
    
    public void deleteAllSchedule(){
        scheduleRepo.deleteAll();
    }
    
    
}
