package org.back.csv.metier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.back.extraction.entities.IncidentResolus;
import org.back.extraction.entities.dto.OlaDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobDate {

	// Calcule SLA
	public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public static Logger logger = LoggerFactory.getLogger(JobDate.class);

	public static int calculeDeadLineTicket(List<String> DateOnBase, Date dateAjourdhuit) throws ParseException {
		int n = 0;

		Date deadlineTicket = null;
		for (String stringDate : DateOnBase) {
			try {

				deadlineTicket = formatter.parse(stringDate);
				if (deadlineTicket.before(dateAjourdhuit)) {
					n += 1;
				}
			} catch (java.text.ParseException exception) {
				System.out.println("error ParseException");
				System.out.println(exception);
			}
		}
		logger.info("fin de clacule deadLineTicket");
		return n;
       
	}

	// Calcule OLA
	public static int calculeDeadLineGroupe(List<String> DateOnBase, Date dateAjourdhuit, int h) throws ParseException {
		int n = 0;
		Date deadlineGroup = null;
		for (String stringDate : DateOnBase) {

//			if(stringDate != null && !stringDate.isEmpty()) {}
			try {
				deadlineGroup = formatter.parse(stringDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(dateAjourdhuit);
				cal.add(11, h);// 11 same to Calendar.HOUR_OF_DAY
				Date future = cal.getTime();
				if (deadlineGroup.before(future)) {
					n += 1;
				}
			} catch (java.text.ParseException exception) {
				System.out.println("error ParseException");
				System.out.println(exception);

			}

		}
		logger.info("fin de clacule deadLineGroupe");
		return n;
	}

	// calcule OlA 2.5 or 2.5+
	public static int calculeDeadLineGroupeDeuxCinq(List<OlaDate> DatesOnBase, Date dateAjourdhuit, double taux)
			throws ParseException {
		int n = 0;
		long difMinute = 0;
		Date deadlineGroupDeuxCinc1 = null;
		Date deadlineGroupDeuxCinc2 = null;
		for (OlaDate stringDate : DatesOnBase) {
//			if(stringDate.getDeadLineGrp() != null && !stringDate.getDeadLineGrp().isEmpty()) {}

			try {
				deadlineGroupDeuxCinc1 = formatter.parse(stringDate.getDeadLineGrp());
				deadlineGroupDeuxCinc2 = formatter.parse(stringDate.getActionDateCreation());
				long difseconds = deadlineGroupDeuxCinc1.getTime() - deadlineGroupDeuxCinc2.getTime();
				difMinute = TimeUnit.MILLISECONDS.toMinutes(difseconds);
				difMinute *= taux;
				Calendar cal = Calendar.getInstance();
				cal.setTime(deadlineGroupDeuxCinc1);
				cal.add(12, (int) difMinute);
				Date futuredeadline = cal.getTime();
				if (futuredeadline.before(dateAjourdhuit)) {
					n += 1;
				}
			} catch (java.text.ParseException exception) {
				System.out.println("error ParseException");
				System.out.println(exception);

			}
		}
		logger.info("fin de clacule deadLineGroupe X 2.5");
		return n;

	}

	// lister les resolus passées hors date ajourd'huit
	public static List<IncidentResolus> deleteResolusHorsDelay(List<IncidentResolus> ListResolusOnBase) {
		List<IncidentResolus> incidentResolusHorsDate = new ArrayList<IncidentResolus>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(new Date());
		for (IncidentResolus incidentResolus : ListResolusOnBase) {

			if (!incidentResolus.getDateResolutions().contains(strDate)) {
//				System.out.println(incidentResolus.toString());
				incidentResolusHorsDate.add(incidentResolus);
			}

		}
		return incidentResolusHorsDate;
	}
}