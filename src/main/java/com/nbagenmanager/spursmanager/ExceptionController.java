/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager;

import com.nbagenmanager.spursmanager.service.ExceedSalaryException;
import com.nbagenmanager.spursmanager.service.PlayerNotFoundException;
import com.nbagenmanager.spursmanager.service.PositionalRequirementException;
import com.nbagenmanager.spursmanager.service.TeamSizeException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Ryan Chin
 */
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(TeamSizeException.class)
    public ResponseEntity<?> handleTeamSizeException(TeamSizeException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(ExceedSalaryException.class)
    public ResponseEntity<?> handleExceedSalaryException(ExceedSalaryException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<?> handlePlayerNotFoundException(PlayerNotFoundException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(PositionalRequirementException.class)
    public ResponseEntity<?> handlePositionalRequirementException(PositionalRequirementException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }
}
