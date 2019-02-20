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

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class IncidentEncours extends Incident{

	@CsvBindByPosition(position = 20)
	private int priorite;

	@CsvBindByPosition(position = 19)
	private String intervenant;

	@CsvBindByPosition(position = 4)
//	@CsvDate("dd/MM/yyyy HH:mm:ss")
	private String Date_SLA;

	@CsvBindByPosition(position = 5)
//	@CsvDate("dd/MM/yyyy HH:mm:ss")
	private String deadLineGrp;

	@CsvBindByPosition(position = 9)
	private String groupeEncharge;

	@CsvBindByPosition(position = 30)
	private String actionDateCreation;
	
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_domain")
    private Domain domain;

//	@CsvBindByPosition(position = 34)
//	public String statut;

	// POUR T LES INCIDENTS TypeOfTreatment=I
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<IncidentEncours> GetCsvListIncident(String Path, String TypeOfTreatment) {
		CSVReader csvReader = null;
		List<IncidentEncours> list = null;
		List<IncidentEncours> listCleanTreatment = new ArrayList<>();
		try {
			csvReader = new CSVReader(new FileReader(Path), ';', '"', 2);
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
			mappingStrategy.setType(this.getClass());
			@SuppressWarnings("rawtypes")
			CsvToBean ctb = new CsvToBean();
			list = ctb.parse(mappingStrategy, csvReader);
//     		list = list.stream().filter(x -> x.getNumero().startsWith("I"))
//				.collect(Collectors.toList());
			for (IncidentEncours incidentEncours : list) {
				if (incidentEncours.getNumero() != null && !incidentEncours.getNumero().isEmpty()
						&& incidentEncours.getNumero().startsWith(TypeOfTreatment)) {
					listCleanTreatment.add(incidentEncours);

				}
			}

		} catch (FileNotFoundException e) {

			System.out.println("Fichier non trouvé "+ Path);
			
		}
        
		catch(java.lang.RuntimeException e) {
			System.out.println("error dans la lecture du file Encours"+e.getMessage());
			
		}
		return listCleanTreatment;
	}

}
