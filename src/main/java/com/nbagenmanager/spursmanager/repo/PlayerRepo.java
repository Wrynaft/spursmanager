/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager.repo;

import com.nbagenmanager.spursmanager.model.Player;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ryan Chin
 */
public interface PlayerRepo extends JpaRepository<Player, Long>{
    void deletePlayerById(Long id);
    Optional<Player> findPlayerById(Long id);
}
