package org.back.extraction.dao;

import org.back.extraction.entities.IncidentRecus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IncidentRecusRepository extends JpaRepository<IncidentRecus,String>{
	
	@Query("Select count(*) from IncidentRecus I where I.domain.id=:x")
	public int RecusByDomain(@Param("x") Long id);

}
