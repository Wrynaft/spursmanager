/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager.repo;

/**
 *
 * @author Ryan Chin
 */
import com.nbagenmanager.spursmanager.model.Player;
import com.nbagenmanager.spursmanager.model.Schedule;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepo extends JpaRepository<Schedule, Integer>{
    
}
