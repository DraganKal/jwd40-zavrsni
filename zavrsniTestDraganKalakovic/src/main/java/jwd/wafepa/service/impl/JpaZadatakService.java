package jwd.wafepa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Stanje;
import jwd.wafepa.model.Zadatak;
import jwd.wafepa.repository.StanjeRepository;
import jwd.wafepa.repository.ZadatakRepository;
import jwd.wafepa.service.ZadatakService;
//Implementacija servisa za Zadatak
@Service
public class JpaZadatakService implements ZadatakService{
	
	@Autowired
	private ZadatakRepository zadatakRepository;
	@Autowired
	private StanjeRepository stanjeRepository;

//	Dobavljanje Zadatka po id-u
	@Override
	public Zadatak findOne(Long id) {
		// TODO Auto-generated method stub
		return zadatakRepository.findOne(id);
	}

//	Dobavljanje svih Zadataka(paginirano)
	@Override
	public Page<Zadatak> findAll(int pageNum) {
		// TODO Auto-generated method stub
		return zadatakRepository.findAll( new PageRequest(pageNum, 5));
	}

//	Cuvanje Zadatka u bazu, ako Zadatak nema stanje, dodaje mu se inicijalno stanje-Nov
//	Izracunavanje ukupnih bodova za Sprint Zadatka koji secuva
	@Override
	public Zadatak save(Zadatak zadatak) {
		if(zadatak.getStanje() == null) {
			zadatak.setStanje(stanjeRepository.findOne(1L));
		}
		int ukupnoBodova = 0;
		List<Zadatak> zad = zadatak.getSprint().getZadaci();
		
		for(Zadatak it : zad) {
			ukupnoBodova += it.getBodovi();
		}
		zadatak.getSprint().setUkupnoBodova(ukupnoBodova);
		return zadatakRepository.save(zadatak);
	}

//	Cuvanje liste Zadataka u bazu
	@Override
	public List<Zadatak> save(List<Zadatak> zadaci) {
		// TODO Auto-generated method stub
		return zadatakRepository.save(zadaci);
	}

//	Brisanje Zadatka po id-u
	@Override
	public Zadatak delete(Long id) {
		Zadatak zadatak = zadatakRepository.findOne(id);
		if(zadatak == null){
			return null;
		}
		zadatakRepository.delete(zadatak);
		return zadatak;
	}

//	Pretraga Zadatka po imenu i sprintu
	@Override
	public Page<Zadatak> search(String ime, Long sprintId, int pageNum) {
		if(ime != null) {
			ime = '%' + ime + '%';
		}
		return zadatakRepository.search(ime, sprintId, new PageRequest(pageNum, 5));
	}

//	Implementacija metode za promenu Stanja odredjenog Zadatka
	@Override
	public Stanje predji(Long id) {
		Zadatak z = findOne(id);
		
		if(z != null) {
			Stanje s = null;
			if(z.getStanje().getId() != 3L) {
				s = stanjeRepository.findOne(z.getStanje().getId() + 1);
				z.setStanje(s);
				s.addZadatak(z);
				zadatakRepository.save(z);
				stanjeRepository.save(s);
						
			}
			
			return s;
		}
		else {
			throw new IllegalArgumentException("Stanje je maksimalno");
		}
	}

}
