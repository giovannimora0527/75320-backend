/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Oskr
 */
public interface RecetaRepository extends JpaRepository<Receta,Long>{
    
}

