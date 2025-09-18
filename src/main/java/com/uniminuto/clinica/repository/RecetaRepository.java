/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uniminuto.clinica.repository;

import com.uniminuto.clinica.entity.Receta;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DELL
 */
@Repository
public interface RecetaRepository extends JpaRepository<Receta, Integer> {
    Optional<Receta> findById(int id);
    Optional<Receta> findByIndicaciones (String indicaciones);
}


