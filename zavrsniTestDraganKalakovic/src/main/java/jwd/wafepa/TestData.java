package jwd.wafepa;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Sprint;
import jwd.wafepa.model.Stanje;
import jwd.wafepa.model.Zadatak;
import jwd.wafepa.service.SprintService;
import jwd.wafepa.service.StanjeService;
import jwd.wafepa.service.ZadatakService;

// Test klasa. Dodavanje entiteta zbog testiranja
@Component
public class TestData {
	
	@Autowired
	private SprintService sprintService;
	@Autowired
	private ZadatakService zadatakService;
	@Autowired
	private StanjeService stanjeService;

	
	


	
	@PostConstruct
	public void init() {
		
		Sprint sprint1 = new Sprint();
		sprint1.setIme("Sprint 1");
		sprintService.save(sprint1);
		Sprint sprint2 = new Sprint();
		sprint2.setIme("Sprint 2");
		sprintService.save(sprint2);
		
		
		
		Stanje s1 = new Stanje();
		s1.setIme("Nov");
		stanjeService.save(s1);
		Stanje s2 = new Stanje();
		s2.setIme("U toku");
		stanjeService.save(s2);
		Stanje s3 = new Stanje();
		s3.setIme("Zavrsen");
		stanjeService.save(s3);
		
		Zadatak z1 = new Zadatak();
		z1.setIme("Zadatak1");
		z1.setZaduzeni("Zaduzeni1");
		z1.setBodovi(10);
		z1.setSprint(sprint1);
		zadatakService.save(z1);
		sprintService.save(sprint1);
		
		Zadatak z2 = new Zadatak();
		z2.setIme("Zadatak2");
		z2.setZaduzeni("Zaduzeni2");
		z2.setBodovi(5);
		z2.setSprint(sprint1);
		zadatakService.save(z2);
		sprintService.save(sprint1);
		
		
	}

}
