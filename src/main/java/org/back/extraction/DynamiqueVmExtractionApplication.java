package org.back.extraction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.back.config.metier.Config;
import org.back.config.metier.FileConf;
import org.back.csv.metier.FiltrageIncidenResolus;
import org.back.csv.metier.FiltrageIncident;
import org.back.csv.metier.JobDate;
import org.back.csv.metier.ResolusReturnRemove;
import org.back.extraction.dao.DomainRepository;
import org.back.extraction.dao.ExtractionInfoRepository;
import org.back.extraction.dao.IncidentEncoursRepository;
import org.back.extraction.dao.IncidentRecusRepository;
import org.back.extraction.dao.IncidentResolusRepository;
import org.back.extraction.dao.ResultatRepository;
import org.back.extraction.entities.Domain;
import org.back.extraction.entities.IncidentEncours;
import org.back.extraction.entities.IncidentRecus;
import org.back.extraction.entities.IncidentResolus;
import org.back.extraction.entities.Resultat;
//import org.back.file.operation.JobFile;
//import org.back.selenium.naviguateur.JobNavigateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DynamiqueVmExtractionApplication implements CommandLineRunner {

	@Autowired
	private DomainRepository domainRepository;

	@Autowired
	private IncidentEncoursRepository incidentEncoursRepository;

	@Autowired
	private IncidentRecusRepository incidentRecusRepository;

	@Autowired
	private ResultatRepository resultatRepository;

	@Autowired
	private IncidentResolusRepository incidentResolusRepository;

	@Autowired
	private ExtractionInfoRepository extractionInfoRepository;

	Logger logger = LoggerFactory.getLogger(DynamiqueVmExtractionApplication.class);

	public final static String FileNameEncours = "\\Vue pour Export Actions Encours(Actions Groupe ESOPE-ESOPE _ Vue pour Export).CSV";
	public final static String FileNameRecus = "\\Actions reçues sur la journée(Groupe ESOPE-Vue pour export).CSV";

	public static void main(String[] args) {
		SpringApplication.run(DynamiqueVmExtractionApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		FileConf FConf = Config.configuration(args[0] + "/Configuration.properties");
		logger.trace("la Lecture du fichier de configuration");
//		JobNavigateur.DownaloadDataFromGmail(FConf.getPathToSave());
//		JobFile.decompressAllFils(FConf.getPathToSave());
		incidentResolusRepository.deleteAll(JobDate.deleteResolusHorsDelay(incidentResolusRepository.findAll()));
		List<IncidentEncours> ListIncidentEncoursT_1;
		List<IncidentRecus> ListIncidentRecusT_1;
		ListIncidentEncoursT_1 = incidentEncoursRepository.findAll();
		ListIncidentRecusT_1 = incidentRecusRepository.findAll();
		incidentEncoursRepository.deleteAll();
		incidentRecusRepository.deleteAll();
		resultatRepository.deleteAll();

		List<Domain> ListDomain = domainRepository.findAll();

		// selections des incidents depuis fichier csv selon domain nom ou nom editeur
		List<IncidentEncours> ListIncidentEncours;
		IncidentEncours c = new IncidentEncours();
		ListIncidentEncours = FiltrageIncident.FiltrageIncidentEncours(
				c.GetCsvListIncident(FConf.getPathToSave() + FileNameEncours, "I"), ListDomain);

		incidentEncoursRepository.saveAll(ListIncidentEncours);

		List<IncidentRecus> ListIncidentRecus;
		IncidentRecus ce = new IncidentRecus();
		ListIncidentRecus = FiltrageIncident.FiltrageIncidentRecus(
				ce.GetCsvListIncident(FConf.getPathToSave() + "" + FileNameRecus, "I"), ListDomain);

		incidentRecusRepository.saveAll(ListIncidentRecus);

		if (incidentResolusRepository.findAll().size() > 0) {
			incidentResolusRepository.deleteAll(ResolusReturnRemove.removeReturnResolus(ListIncidentEncours,
					ListIncidentRecus, incidentResolusRepository.findAll()));
		}
		if (ListIncidentEncoursT_1.size() > 0) {
			List<IncidentResolus> ListIncidentResolus = FiltrageIncidenResolus.FiltrageIncidentEncours(
					ListIncidentEncoursT_1, ListIncidentRecusT_1, ListIncidentRecus, ListIncidentEncours);
			incidentResolusRepository.saveAll(ListIncidentResolus);
		}
		for (Domain item : ListDomain) {
			Resultat R = new Resultat();
			try {
				R.setDomain(item);
				R.setEncours(incidentEncoursRepository.EncourByDomain(item.getId(), "Editeur"));
				R.setEditeur(incidentEncoursRepository.EditeurByDomain(item.getId(), "Editeur"));
				R.setP1(incidentEncoursRepository.PrioriteByDomain(item.getId(), "Editeur"));
				R.setRecu(incidentRecusRepository.RecusByDomain(item.getId()));
				R.setNonAffec(incidentEncoursRepository.Nonnaffecter(item.getId(), "Editeur"));
				R.setSlako(JobDate.calculeDeadLineTicket(
						incidentEncoursRepository.deadlineticket(item.getId(), "Editeur"), new Date()));
				R.setOlako(JobDate.calculeDeadLineGroupe(
						incidentEncoursRepository.deadlineGroup(item.getId(), "Editeur"), new Date(), 0));
				R.setOlanko(JobDate.calculeDeadLineGroupe(
						incidentEncoursRepository.deadlineGroup(item.getId(), "Editeur"), new Date(), 2));
				R.setDeux_cinq(JobDate.calculeDeadLineGroupeDeuxCinq(
						incidentEncoursRepository.deadlineGroup_action(item.getId(), "Editeur"), new Date(), 2.5));
				R.setDeux_cinq_plus(JobDate.calculeDeadLineGroupeDeuxCinq(
						incidentEncoursRepository.deadlineGroup_action(item.getId(), "Editeur"), new Date(), 1.5));
				R.setResolu(incidentResolusRepository.ResolusByDomain(item.getId(), "Editeur"));
				System.out.println("fin de resulta 1.5");
				R.setResoluHorsOla(incidentResolusRepository.ResolusByDomainHorsOla(item.getId(), "Editeur"));
				System.out.println("fin de resulta hors ols true");
				System.out.println(R.toString());
			} catch (ParseException e) {

				e.printStackTrace();
			}

			resultatRepository.save(R);
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			String strDate = formatter.format(new Date());
			extractionInfoRepository.setExtratctionDate(strDate, (long) 1);

		}

	}
}
