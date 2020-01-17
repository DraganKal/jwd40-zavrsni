package jwd.wafepa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Stanje;
import jwd.wafepa.repository.StanjeRepository;
import jwd.wafepa.service.StanjeService;
// Implementacija servisa za Stanje
@Service
public class JpaStanjeService implements StanjeService{
	
	@Autowired
	private StanjeRepository stanjeRepository;

//	Dobavljanje Stanja po id-u
	@Override
	public Stanje findOne(Long id) {
		// TODO Auto-generated method stub
		return stanjeRepository.findOne(id);
	}

//	Dobavljanje svih stanja
	@Override
	public List<Stanje> findAll() {
		// TODO Auto-generated method stub
		return stanjeRepository.findAll();
	}

//	Cuvanje Stanja u bazu
	@Override
	public Stanje save(Stanje stanje) {
		// TODO Auto-generated method stub
		return stanjeRepository.save(stanje);
	}

//	Cuvanje liste Stanja u bazu
	@Override
	public List<Stanje> save(List<Stanje> stanja) {
		// TODO Auto-generated method stub
		return stanjeRepository.save(stanja);
	}

//	Brisanje stanja po id-u
	@Override
	public Stanje delete(Long id) {
		Stanje stanje = stanjeRepository.findOne(id);
		if(stanje == null){
			throw new IllegalArgumentException("Pokusavate da obrisete"
					+ "nepostojece stanje");
		}
		stanjeRepository.delete(stanje);
		return stanje;
	}

}
