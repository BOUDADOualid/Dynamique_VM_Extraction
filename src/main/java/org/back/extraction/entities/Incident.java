package org.back.extraction.entities;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.opencsv.bean.CsvBindByPosition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
public class Incident {

	
	@CsvBindByPosition(position = 0)
	@Id
	private String Numero;
    
	


}
