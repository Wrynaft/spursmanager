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
    private List<City> shortestPathDFS;
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
    }
    
    public List<Schedule> findShortestPathDFS() {
        minTotalDistanceDFS = Integer.MAX_VALUE; // Initialize minimum distance to maximum value
        shortestPathDFS = new ArrayList<>(); // Initialize the list to store the shortest path

        City startingCity = nbaGraph.getCityByName("San Antonio Spurs"); // Starting at San Antonio Spurs
        Set<City> visited = new HashSet<>();  // Create a set to keep track of visited cities
        List<City> path = new ArrayList<>();   // Create a list to store the current path
        // Add the starting city to the path and mark it as visited
        path.add(startingCity);
        visited.add(startingCity);
        dfs(startingCity, visited, path, 0);

        // Create a map to hold the data you want to serialize
        Map<String, Object> result = new HashMap<>();
        result.put("shortestPath", shortestPathDFS);
        result.put("minTotalDistance", minTotalDistanceDFS);
        // Create a new schedule list from the shortest path
        List<Schedule> newSchedule = new ArrayList<>();
        for (Integer i=1; i<=shortestPathDFS.size(); i++){
            Schedule schedule = new Schedule(i, shortestPathDFS.get(i-1).getName());
            newSchedule.add(schedule);
        }
        // Save and return the new schedule
       return scheduleRepo.saveAll(newSchedule);
}
    
    private void dfs(City currentCity, Set<City> visited, List<City> path, int currentDistance) {
        if (visited.size() == nbaGraph.getCities().size()) { // If all cities have been visited
            if (currentDistance < minTotalDistanceDFS) { // Check if the current path is shorter than the previously found shortest path
                minTotalDistanceDFS = currentDistance;
                shortestPathDFS = new ArrayList<>(path); // Update the shortest path
            }
            return;
        }
        // Iterate over all adjacent cities and their respective distances
        for (Map.Entry<City, Integer> entry : nbaGraph.getAdjacencyList().get(currentCity).entrySet()) {
            City nextCity = entry.getKey();
            int distance = entry.getValue();
            // If the next city has not been visited
            if (!visited.contains(nextCity)) {
                // Mark the city as visited and add it to the path
                visited.add(nextCity);
                path.add(nextCity);
                // Recursively call DFS for the next city, updating the distance
                dfs(nextCity, visited, path, currentDistance + distance);
                // Backtrack: remove the city from visited and the path
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
