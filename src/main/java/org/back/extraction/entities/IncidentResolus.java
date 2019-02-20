package org.back.extraction.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class IncidentResolus extends Incident{
	
private int priorite;

private String intervenant;

private String Date_SLA;

private String deadLineGrp;

private String groupeEncharge;

private String actionDateCreation;

private String dateResolutions;

//@Column(name="IsOla", columnDefinition="boolean default 'false'")
private boolean IsOla;

@JsonIgnore
@ManyToOne
@JoinColumn(name = "id_domain")
private Domain domain;

}
