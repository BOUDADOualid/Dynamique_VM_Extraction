package org.back.csv.metier;

import java.util.ArrayList;
import java.util.List;

import org.back.extraction.entities.Domain;
import org.back.extraction.entities.IncidentEncours;
import org.back.extraction.entities.IncidentRecus;

public class FiltrageIncident {

	// filtrage des données par domain nom et domain nom editeur liste des encours

	public static List<IncidentEncours> FiltrageIncidentEncours(List<IncidentEncours> ListIncidentEncoursForFiltre,
			List<Domain> ListDomain) {

		List<IncidentEncours> ListIncidentEncoursFiltre = new ArrayList<>();
		for (IncidentEncours IncidentEncour : ListIncidentEncoursForFiltre) {
			for (Domain domain : ListDomain) {
				if ((IncidentEncour.getGroupeEncharge().trim()).equals(domain.getNom().trim())) {
					IncidentEncour.setDomain(domain);
					ListIncidentEncoursFiltre.add(IncidentEncour);

				} else if ((IncidentEncour.getGroupeEncharge().trim()).equals(domain.getNomEditeur().trim())) {
					ListIncidentEncoursFiltre.add(IncidentEncour);
					IncidentEncour.setDomain(domain);
					ListIncidentEncoursFiltre.add(IncidentEncour);
				}
			}
		}
		return ListIncidentEncoursFiltre;
	}

	// filtrage des données par domain nom liste des reçus
	public static List<IncidentRecus> FiltrageIncidentRecus(List<IncidentRecus> ListIncidentRecusForFiltre,
			List<Domain> ListDomain) {
		List<IncidentRecus> ListIncidentRecusFiltre = new ArrayList<>();
		for (IncidentRecus incidentRecus : ListIncidentRecusForFiltre) {
			for (Domain domain : ListDomain) {
				if ((incidentRecus.getGroupeEncharge()).equals(domain.getNom())) {
					incidentRecus.setDomain(domain);
					ListIncidentRecusFiltre.add(incidentRecus);
				}
			}
		}
		return ListIncidentRecusFiltre;

	}

}
