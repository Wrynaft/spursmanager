/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager.model;

/**
 *
 * @author Ryan Chin
 */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;

@Entity
public class Player implements Serializable{
    @Id
    private Long id;
    private String name;
    private int age;
    private double height;
    private double weight;
    private boolean guard;
    private boolean center;
    private boolean forward;
    private double salary;
    private double points;
    private double rebound;
    private double assists;
    private double steals;
    private double blocks;
    private int matchesPlayed;
    private int contractLength;
    
    public Player(){};

    public Player(Long id, String name, int age, double height, double weight, boolean guard, boolean center, boolean forward, double salary, double points, double rebound, double assists, double steals, double blocks, int matchesPlayed, int contractLength) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.guard = guard;
        this.center = center;
        this.forward = forward;
        this.salary = salary;
        this.points = points;
        this.rebound = rebound;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.matchesPlayed = matchesPlayed;
        this.contractLength = contractLength;
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    

    public double getSalary() {
        return salary;
    }

    public double getPoints() {
        return points;
    }

    public double getRebound() {
        return rebound;
    }

    public double getAssists() {
        return assists;
    }

    public double getSteals() {
        return steals;
    }

    public double getBlocks() {
        return blocks;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public int getContractLength() {
        return contractLength;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isGuard() {
        return guard;
    }

    public boolean isCenter() {
        return center;
    }

    public boolean isForward() {
        return forward;
    }

    public void setGuard(boolean guard) {
        this.guard = guard;
    }

    public void setCenter(boolean center) {
        this.center = center;
    }

    public void setForward(boolean forward) {
        this.forward = forward;
    }

    

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public void setRebound(double rebound) {
        this.rebound = rebound;
    }

    public void setAssists(double assists) {
        this.assists = assists;
    }

    public void setSteals(double steals) {
        this.steals = steals;
    }

    public void setBlocks(double blocks) {
        this.blocks = blocks;
    }


    

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public void setContractLength(int contractLength) {
        this.contractLength = contractLength;
    }
    
    
    
    
}
