/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager.model;

public class Edge {
    private final City destination;
    private final int weight;

    public Edge(City destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public City getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
}
