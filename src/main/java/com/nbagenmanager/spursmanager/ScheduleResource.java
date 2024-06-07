/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager;

import com.nbagenmanager.spursmanager.model.Schedule;
import com.nbagenmanager.spursmanager.service.ScheduleService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ryan Chin
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleResource {
    private final ScheduleService scheduleService;
    
    public ScheduleResource(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }
    
    @GetMapping("/shortestPath")
    public ResponseEntity<List<Schedule>> findShortestPath(){
        List<Schedule> schedules = scheduleService.findShortestPathDFS();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }
    
    @GetMapping("/shortestDistance")
    public ResponseEntity<Integer> findShortestDistance(){
        Integer distance = scheduleService.shortestDistance();
        return new ResponseEntity<>(distance, HttpStatus.OK);
    }
}
