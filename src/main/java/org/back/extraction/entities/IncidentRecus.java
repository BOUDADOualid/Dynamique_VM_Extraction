package org.back.extraction.entities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvToBean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author Oualid
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class IncidentRecus extends Incident {


	@CsvBindByPosition(position = 9)
	private String groupeEncharge;
	
	
	@CsvBindByPosition(position = 8)
	private String deadLineGrp;
	
	

	
	
	@JsonIgnore
    @ManyToOne
	@JoinColumn(name = "id_domain")
	private Domain domain;

	// POUR TRAITER LES INCIDENTS TypeOfTreatment=I
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<IncidentRecus> GetCsvListIncident(String Path, String TypeOfTreatment) {
		CSVReader csvReader = null;
		List<IncidentRecus> list = null;
		List<IncidentRecus> listCleanTreatment = new ArrayList<>();
		try {
			csvReader = new CSVReader(new FileReader(Path), ';', '"', 2);
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
			mappingStrategy.setType(this.getClass());
			@SuppressWarnings("rawtypes")
			CsvToBean ctb = new CsvToBean();
			list = ctb.parse(mappingStrategy, csvReader);
			/*
			 * list = list.stream().filter(x -> ((IncidentRecus)
			 * x).getNumero().startsWith(TypeOfTreatment)) .collect(Collectors.toList());
			 */
			for (IncidentRecus incidentRecus : list) {
				if (incidentRecus.getNumero() != null && !incidentRecus.getNumero().isEmpty()
						&& incidentRecus.getNumero().startsWith(TypeOfTreatment)) {
					listCleanTreatment.add(incidentRecus);

				}
			}

		} catch (FileNotFoundException e) {

			System.out.println("Fichier non trouvé dans "+ Path);
		}
		
		catch(java.lang.RuntimeException e) {
			System.out.println("error dans la lecture du file reçu"+e.getMessage());
		}
		return listCleanTreatment;

	}

}
