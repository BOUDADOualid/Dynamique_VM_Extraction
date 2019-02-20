package org.back.extraction.dao;

import javax.transaction.Transactional;

import org.back.extraction.entities.ExtractionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ExtractionInfoRepository extends JpaRepository<ExtractionInfo,Long>{
    
	
	@Transactional
	@Modifying
	@Query("update ExtractionInfo Ex set Ex.dataExtraction= ?1 where Ex.id = ?2")
	int setExtratctionDate(String firstname, Long id);
	
	
}
