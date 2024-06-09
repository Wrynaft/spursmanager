/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager.model;

//Represent distance between cities in the graph
public class Edge {
    private final City destination; //Destination of the edge
    private final int weight; //Weight or distance of the edge, in km

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
