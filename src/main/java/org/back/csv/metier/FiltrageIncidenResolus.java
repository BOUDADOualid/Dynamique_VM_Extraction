package org.back.csv.metier;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.back.extraction.entities.IncidentEncours;
import org.back.extraction.entities.IncidentRecus;
import org.back.extraction.entities.IncidentResolus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FiltrageIncidenResolus {
	static Logger logger = LoggerFactory.getLogger(FiltrageIncidenResolus.class);

	public static List<IncidentResolus> FiltrageIncidentEncours(List<IncidentEncours> ListEncours,
			List<IncidentRecus> ListRecus, List<IncidentRecus> ListRecusNow, List<IncidentEncours> ListEncoursNow) {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<IncidentResolus> ListIncidentResolusFiltre = new ArrayList<>();
		List<IncidentRecus> ListIncidentRecusFilter = new ArrayList<>();

		for (IncidentEncours incidentEncours : ListEncours) {
			if (!ListEncoursNow.contains(incidentEncours) && !incidentEncours.getGroupeEncharge().contains("EDITEUR")) {
				IncidentResolus incidentResolus = new IncidentResolus();
				incidentResolus.setNumero(incidentEncours.getNumero());
				incidentResolus.setPriorite(incidentEncours.getPriorite());
				incidentResolus.setGroupeEncharge(incidentEncours.getGroupeEncharge());
				incidentResolus.setIntervenant(incidentEncours.getIntervenant());
				incidentResolus.setActionDateCreation(incidentEncours.getActionDateCreation());
				incidentResolus.setDate_SLA(incidentEncours.getDate_SLA());
				incidentResolus.setDeadLineGrp(incidentEncours.getDeadLineGrp());
				incidentResolus.setDomain(incidentEncours.getDomain());
				String strDate = dateFormat.format(new Date());
				incidentResolus.setDateResolutions(strDate);

				Date deadlineTicket = null;
				try {
					deadlineTicket = dateFormat.parse(incidentEncours.getDeadLineGrp());
					if (deadlineTicket.before(new Date())) {
						incidentResolus.setIsOla(true);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ListIncidentResolusFiltre.add(incidentResolus);
			}
		}
		Iterator<IncidentResolus> iter = ListIncidentResolusFiltre.iterator();
		while (iter.hasNext()) {
			IncidentResolus IncR = iter.next();
			for (IncidentRecus incidentRecus : ListRecusNow) {
				if (IncR.getNumero().equals(incidentRecus.getNumero())) {
					try {
						iter.remove();
						break;
					} catch (Exception e) {

						logger.info("ConcurrentRemoveExceptions");
					}
				}
			}
		}

		for (IncidentRecus incidentRecus : ListRecus) {
			if (!ListRecusNow.contains(incidentRecus) && !incidentRecus.getGroupeEncharge().contains("EDITEUR")) {
				ListIncidentRecusFilter.add(incidentRecus);
			}
		}

		for (IncidentRecus incidentRecus : ListIncidentRecusFilter) {
			boolean flague = false;
			for (IncidentEncours incidentEncours : ListEncoursNow) {
				if (incidentEncours.getNumero().equals(incidentRecus.getNumero())) {
					flague = true;
					break;
				}
			}
			if (flague == false && !incidentRecus.getGroupeEncharge().contains("EDITEUR")) {
				IncidentResolus incidentResolus = new IncidentResolus();
				incidentResolus.setNumero(incidentRecus.getNumero());
				incidentResolus.setGroupeEncharge(incidentRecus.getGroupeEncharge());
				incidentResolus.setDomain(incidentRecus.getDomain());
				incidentResolus.setDeadLineGrp(incidentRecus.getDeadLineGrp());
				String strDate = dateFormat.format(new Date());
				incidentResolus.setDateResolutions(strDate);

				Date deadlineTicket = null;
				try {
					deadlineTicket = dateFormat.parse(incidentRecus.getDeadLineGrp());
					if (deadlineTicket.before(new Date())) {
						incidentResolus.setIsOla(true);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ListIncidentResolusFiltre.add(incidentResolus);
			}

		}

		return ListIncidentResolusFiltre;

	}
}
