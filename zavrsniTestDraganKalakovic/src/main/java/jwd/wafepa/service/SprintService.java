package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.Sprint;

public interface SprintService {
	
	Sprint findOne(Long id);
	
	List<Sprint> findAll();
	
	Sprint save(Sprint sprint);


}
