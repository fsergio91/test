package com.example.demo.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.example.demo.model.Attivita;
import com.example.demo.model.Categoria;

public interface AttivitaService {
	
	public List<Attivita> getAttivitaByFilters(String descrizione, String giorno);
	
	public void saveAttivita(Attivita a);
	
	public void updateAttivita(Attivita a);
	
	public void deleteAttivita(Long id);
	
	public Attivita findById(Long id);
	
	public void associaCategoria(Long idAttivita, Long idCategoria);
}
