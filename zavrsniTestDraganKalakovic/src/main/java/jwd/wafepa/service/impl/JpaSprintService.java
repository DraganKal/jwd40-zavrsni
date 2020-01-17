package jwd.wafepa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Sprint;
import jwd.wafepa.model.Zadatak;
import jwd.wafepa.repository.SprintRepository;
import jwd.wafepa.service.SprintService;
// Implementacija servisa za Sprint
@Service
public class JpaSprintService implements SprintService{
	
	@Autowired
	private SprintRepository sprintRepository;

//	Dobavljanje Sprinta po id-u
	@Override
	public Sprint findOne(Long id) {
		// TODO Auto-generated method stub
		return sprintRepository.findOne(id);
	}

//	Dobavljanje svih Sprintova
	@Override
	public List<Sprint> findAll() {
		// TODO Auto-generated method stub
		return sprintRepository.findAll();
	}

//	Cuvanje Sprinta u bazu i izracunavanje ukupnih bodova
	@Override
	public Sprint save(Sprint sprint) {
	
		int ukupnoBodova = 0;
		List<Zadatak> zad = sprint.getZadaci();
		
		for(Zadatak it : zad) {
			ukupnoBodova += it.getBodovi();
		}
		sprint.setUkupnoBodova(ukupnoBodova);
		
		return sprintRepository.save(sprint);
	}

}
