/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager.service;

/**
 *
 * @author Ryan Chin
 */
public class ExceedTeamSizeException extends RuntimeException{
    public ExceedTeamSizeException(String message){
        super(message);
    }
}
