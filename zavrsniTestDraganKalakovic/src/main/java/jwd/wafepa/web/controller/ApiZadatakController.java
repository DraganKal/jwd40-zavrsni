package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Stanje;
import jwd.wafepa.model.Zadatak;
import jwd.wafepa.service.ZadatakService;
import jwd.wafepa.support.DTOtoZadatak;
import jwd.wafepa.support.StanjeToDTO;
import jwd.wafepa.support.ZadatakToDTO;
import jwd.wafepa.web.dto.StanjeDTO;
import jwd.wafepa.web.dto.ZadatakDTO;

// Kontroler za Zadatak
@RestController
@RequestMapping(value="/api/zadaci")
public class ApiZadatakController {
	
	@Autowired
	private ZadatakService zadatakService;
	@Autowired
	private ZadatakToDTO toDto;
	@Autowired 
	private DTOtoZadatak toZadatak;
	@Autowired
	private StanjeToDTO stanjeToDto;
	
//	Dobavljanje svih zadataka(paginirano)
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<ZadatakDTO>> getZadaci(
			@RequestParam(required=false) String ime,
			@RequestParam(required=false) Long sprintId,
			@RequestParam(value="pageNum", defaultValue="0") int pageNum){
		
		Page<Zadatak> zadaciPage = null;
		if(ime != null || sprintId != null) {
			zadaciPage = zadatakService.search(ime, sprintId, pageNum);
		}
		else {

			zadaciPage = zadatakService.findAll(pageNum);
		}
		
		
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(zadaciPage.getTotalPages()) );
		
		return new ResponseEntity<>(
				toDto.convert(zadaciPage.getContent()),
				headers,
				HttpStatus.OK);
	}
	
//	Dobavljanje Zadatka po id-u
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<ZadatakDTO> getZadatak(@PathVariable Long id){
		Zadatak zadatak = zadatakService.findOne(id);
		if(zadatak==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDto.convert(zadatak),
				HttpStatus.OK);
	}
	
//	Dodavanje Zadatka u bazu
	@RequestMapping(method=RequestMethod.POST,
			consumes="application/json")
	public ResponseEntity<ZadatakDTO> add(
		@Validated @RequestBody ZadatakDTO newZadatakDTO){
	
	Zadatak saved = zadatakService.save(
			toZadatak.convert(newZadatakDTO));
	
	return new ResponseEntity<>(
			toDto.convert(saved), 
			HttpStatus.CREATED);
	}
	
//	Izmena postojeceg Zadatka
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}",
			consumes="application/json")
	public ResponseEntity<ZadatakDTO> edit(
			@Validated @RequestBody ZadatakDTO zadatakDTO,
			@PathVariable Long id){
		
		if(!id.equals(zadatakDTO.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Zadatak persisted = zadatakService.save(
				toZadatak.convert(zadatakDTO));
		
		return new ResponseEntity<>(
				toDto.convert(persisted),
				HttpStatus.OK);
	}
	
//	Brisanje odredjenog Zadatka
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<ZadatakDTO> delete(@PathVariable Long id){
		Zadatak deleted = zadatakService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDto.convert(deleted),
				HttpStatus.OK);
	}
	@RequestMapping(method=RequestMethod.POST, value="/{id}")
	public ResponseEntity<StanjeDTO> predji(@PathVariable Long id){
		 
		Stanje s = zadatakService.predji(id);
		
		if(s != null) {
			return new ResponseEntity<>(stanjeToDto.convert(s),
					HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(
					HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
