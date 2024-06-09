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
public class Schedule implements Serializable{
    @Id
    private Integer Id; //Primary key and also represents sequence of city visted
    private String cityName;
    
    public Schedule(){};

    public Schedule(Integer Id, String cityName) {
        this.Id = Id;
        this.cityName = cityName;
    }

    public int getId() {
        return Id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    
}
