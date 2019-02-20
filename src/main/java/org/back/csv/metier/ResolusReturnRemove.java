package org.back.csv.metier;

import java.util.ArrayList;
import java.util.List;
import org.back.extraction.entities.IncidentEncours;
import org.back.extraction.entities.IncidentRecus;
import org.back.extraction.entities.IncidentResolus;

public class ResolusReturnRemove {

	public static List<IncidentResolus> removeReturnResolus(List<IncidentEncours> listEncoursNow,
			List<IncidentRecus> listRecusNow,List<IncidentResolus> ListResolus) {
		List<IncidentResolus>ListRemove=new ArrayList<IncidentResolus>();
		
		for (IncidentEncours IncidentEncours : listEncoursNow) {
			for (IncidentResolus incidentResolus : ListResolus) {
			         if(IncidentEncours.getNumero().equals(incidentResolus.getNumero())) {
			        	 ListRemove.add(incidentResolus);
			        	 break;
			        	 
			         }
			}		
		}

		for (IncidentRecus incidentRecus : listRecusNow) {
			for (IncidentResolus incidentResolus : ListResolus) {
		         if(incidentRecus.getNumero().equals(incidentResolus.getNumero())) {
		        	 ListRemove.add(incidentResolus);
		        	 break;	 
		         }
			
		}
		}
		
		return ListRemove;
	}
	
}


