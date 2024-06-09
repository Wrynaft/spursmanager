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

//Entity class for injury reserve
@Entity
public class InjuryReserve {
    @Id
    private Long Id; //Player ID
    private String name; //Player name
    private Long seq; //Sequence of player in the injury reserve stack

    public InjuryReserve() {
    }

    public InjuryReserve(Long Id, String name, Long seq) {
        this.Id = Id;
        this.name = name;
        this.seq = seq;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public Long getSeq() {
        return seq;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
