/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 *
 * @author Ryan Chin
 */
@Entity
public class Contract {
    @Id
    private Long Id;
    private String name;
    private double points;
    private Long seq;

    public Contract() {
    }

    public Contract(Long Id, String name, double points, Long seq) {
        this.Id = Id;
        this.name = name;
        this.points = points;
        this.seq = seq;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public double getPoints() {
        return points;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }
    
}
