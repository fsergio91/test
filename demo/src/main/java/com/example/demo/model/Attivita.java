package com.example.demo.model;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.*;

@Entity
public class Attivita implements Serializable{
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Long id;
	  
	  private String descrizione;
	  
	  private String luogoSvolgimento;
	  
	  private Date dataInizio;
	  
	  private Date dataFine;
	  
	  private Time oraInizio;
	
	  private Time oraFine;
	  
	  private int numeroPostiDisponibili;
	  
	  private int numeroPostiOccupati;
	  
	    @ManyToMany
	    @JoinTable(name = "attivita_categorie",
	        joinColumns = @JoinColumn(name = "attivita_id"),
	        inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	    private List<Categoria> categorie = new ArrayList<>();


	public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getLuogoSvolgimento() {
		return luogoSvolgimento;
	}

	public void setLuogoSvolgimento(String luogoSvolgimento) {
		this.luogoSvolgimento = luogoSvolgimento;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Time getOraInizio() {
		return oraInizio;
	}

	public void setOraInizio(Time oraInizio) {
		this.oraInizio = oraInizio;
	}

	public Time getOraFine() {
		return oraFine;
	}

	public void setOraFine(Time oraFine) {
		this.oraFine = oraFine;
	}

	public int getNumeroPostiDisponibili() {
		return numeroPostiDisponibili;
	}

	public void setNumeroPostiDisponibili(int numeroPostiDisponibili) {
		this.numeroPostiDisponibili = numeroPostiDisponibili;
	}

	public int getNumeroPostiOccupati() {
		return numeroPostiOccupati;
	}

	public void setNumeroPostiOccupati(int numeroPostiOccupati) {
		this.numeroPostiOccupati = numeroPostiOccupati;
	}

	public List<Categoria> getCategorie() {
		return categorie;
	}

	public void setCategorie(List<Categoria> categorie) {
		this.categorie = categorie;
	}

}
