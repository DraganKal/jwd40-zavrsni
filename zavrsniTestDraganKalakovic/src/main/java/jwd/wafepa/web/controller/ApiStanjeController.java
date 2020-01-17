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

import jwd.wafepa.model.Stanje;
import jwd.wafepa.service.StanjeService;
import jwd.wafepa.support.StanjeToDTO;
import jwd.wafepa.web.dto.StanjeDTO;

// Kontroler za Stanje
@RestController
@RequestMapping(value="/api/stanja")
public class ApiStanjeController {
	
	@Autowired
	private StanjeService stanjeService;
	@Autowired
	private StanjeToDTO toDto;
	
//	Dobavljanje svih Stanja
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<StanjeDTO>> getStanja(){
		
		List<Stanje> stanja = stanjeService.findAll();
		
		return new ResponseEntity<>(
				toDto.convert(stanja), 
				HttpStatus.OK);
	}
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
