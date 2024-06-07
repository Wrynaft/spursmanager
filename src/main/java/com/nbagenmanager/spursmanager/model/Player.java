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
    private Long id; //Player ID and also primary key
    private String name;
    private int age;
    private double height;
    private double weight;
    private String position;
    private double salary;
    private double points;
    private double rebound;
    private double assists;
    private double steals;
    private double blocks;
    private int matchesPlayed;
    private int contractLength;
    
    public Player(){};
    
    //Player constructor
    public Player(Long id, String name, int age, double height, double weight, String position, double salary, double points, double rebound, double assists, double steals, double blocks, int matchesPlayed, int contractLength) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.position = position;
        this.salary = salary;
        this.points = points;
        this.rebound = rebound;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.matchesPlayed = matchesPlayed;
        this.contractLength = contractLength;
    }

   //Create getters and setters
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

    public String getPosition(){
        return position;
    }

    public void setPosition(String position){
        this.position = position;
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
