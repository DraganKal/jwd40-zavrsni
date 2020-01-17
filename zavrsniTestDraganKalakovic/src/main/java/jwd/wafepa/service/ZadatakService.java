package jwd.wafepa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import jwd.wafepa.model.Stanje;
import jwd.wafepa.model.Zadatak;

public interface ZadatakService {
	
	Zadatak findOne(Long id);
	
	Page<Zadatak> findAll( int pageNum);
	
	
	
	Zadatak save(Zadatak zadatak);
	
	List<Zadatak> save(List<Zadatak> zadaci);
	
	Zadatak delete(Long id);
	
	Page<Zadatak> search(
			@Param("ime") String ime, 
			@Param("sprintId") Long sprintId, 
			 int pageNum);
	Stanje predji(Long id);

}
