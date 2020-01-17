package jwd.wafepa.web.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Sprint;
import jwd.wafepa.service.SprintService;
import jwd.wafepa.support.SprintToDTO;
import jwd.wafepa.web.dto.SprintDTO;

// Kontroler za Sprint
@RestController
@RequestMapping(value="/api/sprintovi")
public class ApiSprintController {
	
	@Autowired
	private SprintService sprintService;
	@Autowired
	private SprintToDTO toDto;
	
//	Dobavljanje svih Sprintova
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<SprintDTO>> getSprintovi(){
		
		List<Sprint> sprintovi = sprintService.findAll();
		
		return new ResponseEntity<>(
				toDto.convert(sprintovi), 
				HttpStatus.OK);
	}
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
