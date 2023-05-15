package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Attivita;
import com.example.demo.model.Categoria;
import com.example.demo.repository.AttivitaRepository;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.service.AttivitaService;

@Service("attivitaService")
public class AttivitaServiceImpl implements AttivitaService{
	
	@Autowired
	private AttivitaRepository attivitaRepository;
	
    @Autowired
    private CategoriaRepository categoriaRepository;
    
	public List<Attivita> getAttivitaByFilters(String descrizione, String giorno){
		return attivitaRepository.findAttivitaByFilters(descrizione, giorno);
	}
	
	public void saveAttivita(Attivita a){
		attivitaRepository.save(a);
	}
	
	public void updateAttivita(Attivita a){
		Attivita found =  attivitaRepository.findById(a.getId()).orElse(null);
		if(Objects.nonNull(found)) {
			found.setLuogoSvolgimento(a.getLuogoSvolgimento());
			found.setDataFine(a.getDataFine());
			found.setDataInizio(a.getDataInizio());
			found.setOraFine(a.getOraFine());
			found.setOraInizio(a.getOraInizio());
			found.setDescrizione(a.getDescrizione());
			found.setNumeroPostiDisponibili(a.getNumeroPostiDisponibili());
			found.setNumeroPostiOccupati(a.getNumeroPostiOccupati());
			attivitaRepository.saveAndFlush(found);
		}
	}
	
	public Attivita findById(Long idAttivita){
		return attivitaRepository.findById(idAttivita).orElse(null);
	}
	
	public void deleteAttivita(Long idAttivita){
		Attivita a = attivitaRepository.findById(idAttivita).orElse(null);
		if(Objects.nonNull(a)) {
			attivitaRepository.delete(a);
		}	
	}
	
	public void associaCategoria(Long idAttivita, Long idCategoria){
		Attivita attivita = attivitaRepository.findById(idAttivita).orElse(null);
		Categoria categoria = categoriaRepository.findById(idCategoria).orElse(null);
		
		if(Objects.nonNull(categoria) && Objects.nonNull(attivita)) {
			Categoria found = attivita.getCategorie().stream().
					 filter(c -> c.getId()== idCategoria)
				    .findFirst()
				    .orElse(null);
			if(!Objects.nonNull(found)) {
				attivita.getCategorie().add(categoria);
				attivitaRepository.saveAndFlush(attivita);
			}
		}

	}
}
