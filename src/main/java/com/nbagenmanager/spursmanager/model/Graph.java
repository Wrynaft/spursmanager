/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager.model;

import java.util.*;

public class Graph {
    private List<City> cities;
    private Map<City, Map<City, Integer>> adjacencyList;

    public Graph() {
        cities = new ArrayList<>();
        adjacencyList = new HashMap<>();
    }

    public void addCity(City city) {
        cities.add(city);
        adjacencyList.put(city, new HashMap<>());
    }

    public void addEdgeBidirectional(City from, City to, int distance) {
        adjacencyList.get(from).put(to, distance);
        adjacencyList.get(to).put(from, distance);
    }

    public List<City> getCities() {
        return cities;
    }

    public Map<City, Map<City, Integer>> getAdjacencyList() {
        return adjacencyList;
    }

    // Method to get a city by name
    public City getCityByName(String name) {
        for (City city : cities) {
            if (city.getName().equals(name)) {
                return city;
            }
        }
        return null; // Return null if the city is not found
    }
}

