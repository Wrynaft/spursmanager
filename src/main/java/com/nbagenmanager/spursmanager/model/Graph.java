/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager.model;

import java.util.*;

public class Graph {
    private final Map<City, Map<City, Integer>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addCity(City city) {
        adjacencyList.putIfAbsent(city, new HashMap<>());
    }

    public void addEdge(City source, City destination, int weight) {
        adjacencyList.get(source).put(destination, weight);
    }

    public void addEdgeBidirectional(City source, City destination, int weight) {
        addEdge(source, destination, weight);
        addEdge(destination, source, weight);
    }

    public Map<City, Map<City, Integer>> getAdjacencyList() {
        return adjacencyList;
    }

    public int getDistance(City source, City destination) {
        return adjacencyList.get(source).get(destination);
    }


    public List<City> findShortestPathDFS(City startCity, City endCity) {
        List<City> visited = new ArrayList<>();
        List<City> shortestPath = new ArrayList<>();
        shortestPath.add(startCity);

        dfs(startCity, startCity, visited, shortestPath);

        return shortestPath;
    }

    private boolean dfs(City startCity, City currentCity, List<City> visited, List<City> shortestPath) {
        visited.add(currentCity);

        if (visited.size() == adjacencyList.size() && currentCity.equals(startCity)) {
            return true; // All cities visited and returned to the start city
        }

        Map<City, Integer> neighbors = adjacencyList.get(currentCity);
        for (City neighbor : neighbors.keySet()) {
            if (!visited.contains(neighbor)) {
                shortestPath.add(neighbor);
                if (dfs(startCity, neighbor, visited, shortestPath)) {
                    return true;
                }
                shortestPath.remove(shortestPath.size() - 1);
            }
        }

        visited.remove(currentCity);
        return false;
    }
}

