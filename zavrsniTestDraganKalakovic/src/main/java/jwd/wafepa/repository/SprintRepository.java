package jwd.wafepa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long>{

}
