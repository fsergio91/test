package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AssociazioneAttivitaCategoriaDto;
import com.example.demo.model.Attivita;
import com.example.demo.service.AttivitaService;

@RestController
@RequestMapping("/attivita")
public class AttivitaController {
	
    @Autowired
    private AttivitaService attivitaService;
    
    @GetMapping
    public ResponseEntity<?> cercaAttivita(
            @RequestParam(required = false) String descrizione,
            @RequestParam(required = false) String giorno
    ) {
    	try {
    		List<Attivita> attivitaList = attivitaService.getAttivitaByFilters(descrizione, giorno);
            return ResponseEntity.ok(attivitaList);
    	}
    	catch (Exception e) {
            return new ResponseEntity<>("Errore non specificato", HttpStatus.INTERNAL_SERVER_ERROR);
        }	
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> cercaAttivitaById(
    		@PathVariable Long id
    ) {
    	try {
    		Attivita attivita = attivitaService.findById(id);
            return ResponseEntity.ok(attivita);
    	}
    	catch (Exception e) {
            return new ResponseEntity<>("Errore non specificato", HttpStatus.INTERNAL_SERVER_ERROR);
        }	
        
    }
	
    @PostMapping
    public ResponseEntity<?> creaAttivita(@RequestBody Attivita attivita) {
    	try {
    		attivitaService.saveAttivita(attivita);
            return new ResponseEntity<>(attivita, HttpStatus.CREATED);
    	}
    	catch (Exception e) {
            return new ResponseEntity<>("Errore nella creazione", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    @PutMapping
    public ResponseEntity<?> aggiornaAttivita(@RequestBody Attivita attivita) {
    	try {
    		attivitaService.updateAttivita(attivita);
            return new ResponseEntity<>(attivita, HttpStatus.CREATED);
    	}
    	catch (Exception e) {
            return new ResponseEntity<>("Errore nell' aggiornamento", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancellaAttivita(
    		@PathVariable Long id
    ) {
    	try {
    		attivitaService.deleteAttivita(id);
            return new ResponseEntity<>("Cancellazione avvenuta", HttpStatus.CREATED);
    	}
    	catch (Exception e) {
            return new ResponseEntity<>("Errore non specificato", HttpStatus.INTERNAL_SERVER_ERROR);
        }	
        
    }
    
    @PostMapping("/associa")
    public ResponseEntity<?> associaAttivitaCategoria(
    		@RequestBody AssociazioneAttivitaCategoriaDto dto) {
    	try {
    		attivitaService.associaCategoria(dto.getIdAttivita(), dto.getIdCategoria());
            return new ResponseEntity<>("Associazione creata", HttpStatus.CREATED);
    	}
    	catch (Exception e) {
            return new ResponseEntity<>("Errore nell'associazione", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

}
