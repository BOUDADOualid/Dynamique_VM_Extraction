package org.back.extraction.Metier;

import org.back.extraction.dao.IncidentEncoursRepository;
import org.back.extraction.entities.Domain;
import org.back.extraction.entities.IncidentEncours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidentEncoursMetierImpl implements IncidentEncoursMetier {

	@Autowired
	private IncidentEncoursRepository incidentEncoursRepository;

	public IncidentEncours AjouterIncidentEncours(IncidentEncours incidentEncours, Domain D) {
		IncidentEncours inncident_encours = new IncidentEncours();
		inncident_encours.setDomain(D);
		inncident_encours.setPriorite(incidentEncours.getPriorite());
		inncident_encours.setGroupeEncharge(incidentEncours.getGroupeEncharge());
		inncident_encours.setIntervenant(incidentEncours.getIntervenant());
		inncident_encours.setDeadLineGrp(incidentEncours.getDeadLineGrp());
		incidentEncours.setDate_SLA(incidentEncours.getDate_SLA());
		incidentEncours.setActionDateCreation(incidentEncours.getActionDateCreation());
		return incidentEncoursRepository.save(inncident_encours);
	}

}
