package com.example.demo.repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Attivita;

@Repository("attivitaRepository")
public interface AttivitaRepository extends JpaRepository<Attivita, Long> {
    
    @Query("SELECT a FROM Attivita a " +
            "WHERE (:descrizione IS NULL OR lower(a.descrizione) LIKE lower(concat('%', :descrizione, '%'))) " +
            "AND (:giorno IS NULL OR TO_DATE(:giorno,'dd/mm/yyyy') BETWEEN a.dataInizio and a.dataFine) " +
            "AND a.numeroPostiDisponibili > 0")
    List<Attivita> findAttivitaByFilters(@Param("descrizione") String nome, @Param("giorno") String giorno);

}
