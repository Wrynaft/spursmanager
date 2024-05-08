/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager;

import com.nbagenmanager.spursmanager.model.Player;
import com.nbagenmanager.spursmanager.service.FreeAgentService;
import java.util.List;
import org.json.simple.parser.ParseException;
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
@RequestMapping("/freeagents")
public class FreeAgentResource {
    private final FreeAgentService freeAgentService;

    public FreeAgentResource(FreeAgentService freeAgentService) {
        this.freeAgentService = freeAgentService;
    }
    
    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayer() throws ParseException{
        List<Player> response = freeAgentService.getFreeAgents();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
