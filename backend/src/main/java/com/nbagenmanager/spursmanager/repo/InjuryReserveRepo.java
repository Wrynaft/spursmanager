/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager.repo;

import com.nbagenmanager.spursmanager.model.InjuryReserve;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ryan Chin
 */
public interface InjuryReserveRepo extends JpaRepository<InjuryReserve, Long>{
    List<InjuryReserve> findAllByOrderBySeqAsc();
    List<InjuryReserve> findAllByOrderBySeqDesc();
    void deleteInjuryReserveById(Long id);
}
