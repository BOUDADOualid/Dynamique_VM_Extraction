package org.back.extraction.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Domain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	private String nom;
	private String nomEditeur;

	@Cascade(CascadeType.DELETE)
	@JsonIgnore
	@OneToMany(mappedBy = "domain", fetch = FetchType.LAZY)
	private List<IncidentEncours> ListIncidentEncours;

	@Cascade(CascadeType.DELETE)
	@JsonIgnore
	@OneToMany(mappedBy = "domain", fetch = FetchType.LAZY)
	private List<IncidentRecus> ListIncidentRecus;
	
	@Cascade(CascadeType.DELETE)
	@JsonIgnore
	@OneToMany(mappedBy="domain",fetch=FetchType.LAZY)
	private List<IncidentResolus> ListResolus;
	
	@Cascade(CascadeType.DELETE)
	@JsonIgnore
	@OneToMany(mappedBy="domain",fetch=FetchType.LAZY)
	private List<Resultat> ListResultat;
	
	@Override
	public String toString() {
		return "Domain [id=" + id + ", nom=" + nom + ", nomEditeur=" + nomEditeur + "]";
	}
}
