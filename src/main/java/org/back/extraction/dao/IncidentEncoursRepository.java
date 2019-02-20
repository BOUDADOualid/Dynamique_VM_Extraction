package org.back.extraction.dao;

import java.util.List;

import org.back.extraction.entities.IncidentEncours;
import org.back.extraction.entities.dto.OlaDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface IncidentEncoursRepository extends JpaRepository<IncidentEncours, String> {
	
	// to count number of incident by domain with name domain
		@Query("select count(*) from IncidentEncours I where I.domain.id=:x AND I.groupeEncharge NOT like %:motCle%")
		public int EncourByDomain(@Param("x") Long id, @Param("motCle") String mc);

		// to count number of incident by domain with nameEditeur
		@Query("select count(*) from IncidentEncours I where I.domain.id=:x AND I.groupeEncharge like %:motCle%")
		public int EditeurByDomain(@Param("x") Long id, @Param("motCle") String mc);
		
		//to count priorité par domaine 
		@Query("Select count(*) from IncidentEncours I where I.domain.id=:x AND priorite=1 AND I.groupeEncharge NOT like %:motCle% ")
		public int PrioriteByDomain(@Param("x") Long id, @Param("motCle") String mc);
		
		//to count not affected
		@Query("Select count(*) from IncidentEncours I where I.domain.id=:x and LENGTH(I.intervenant)=0 AND I.groupeEncharge NOT like %:motCle%")
		public int Nonnaffecter(@Param("x") Long id, @Param("motCle") String mc);
		
		//select date sal deadlineticket
		@Query("Select Date_SLA from IncidentEncours I where I.domain.id=:x AND I.groupeEncharge NOT like %:motCle%")
		public List<String> deadlineticket(@Param("x") Long id,@Param("motCle") String mc);
		
		
		//select data ola
		@Query("Select deadLineGrp from IncidentEncours I where I.domain.id=:x AND I.groupeEncharge NOT like %:motCle%")
		public List<String> deadlineGroup(@Param("x") Long id,@Param("motCle") String mc);
		
		@Query("select new org.back.extraction.entities.dto.OlaDate(I.deadLineGrp,I.actionDateCreation) from IncidentEncours I where I.domain.id=:x AND I.groupeEncharge NOT like %:motCle%")
		public List<OlaDate>deadlineGroup_action(@Param("x") Long id,@Param("motCle") String mc);
		

} 
