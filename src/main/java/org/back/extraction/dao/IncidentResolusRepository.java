package org.back.extraction.dao;

import org.back.extraction.entities.IncidentResolus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IncidentResolusRepository extends JpaRepository<IncidentResolus,String> {

	// to count number of incident Resolus 
		@Query("select count(*) from IncidentResolus I where I.domain.id=:x AND I.groupeEncharge NOT like %:motCle%")
			public int ResolusByDomain(@Param("x") Long id, @Param("motCle") String mc);
	
		
		@Query("select count(*) from IncidentResolus I where I.domain.id=:x AND I.groupeEncharge NOT like %:motCle% AND I.IsOla=true ")
		public int ResolusByDomainHorsOla(@Param("x") Long id, @Param("motCle") String mc); 	
		
		
		
}
