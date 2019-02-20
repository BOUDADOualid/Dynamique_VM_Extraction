package org.back.extraction.dao;
import org.back.extraction.entities.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainRepository extends JpaRepository<Domain,Long> {

}
