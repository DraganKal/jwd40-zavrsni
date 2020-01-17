package jwd.wafepa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Zadatak;

@Repository
public interface ZadatakRepository extends JpaRepository<Zadatak, Long>{
	
	
//	Pretraga zadatka po imenu i sprintu
	
	@Query("SELECT z FROM Zadatak z WHERE "
			+ "(:ime IS NULL OR z.ime like :ime) AND "
			+ "(:sprintId IS NULL OR z.sprint.id = :sprintId) "
			)
	Page<Zadatak> search(
			@Param("ime") String ime, 
			@Param("sprintId") Long sprintId,  
			Pageable pageRequest);

}
