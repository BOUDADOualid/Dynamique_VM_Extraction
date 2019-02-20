package org.back.extraction.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Resultat {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	private int encours;
	private int p1;
	private int nonAffec;
	private int slako;
	private int olako;
	private int olanko;
	private int deux_cinq;
	private int deux_cinq_plus;
	private int editeur;
	private int recu;
	private int resolu;
	private int resoluHorsOla;

	
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_domain")
	private Domain domain;

	

}
