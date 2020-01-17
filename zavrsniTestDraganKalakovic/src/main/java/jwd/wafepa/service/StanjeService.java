package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.Stanje;

public interface StanjeService {
	
	Stanje findOne(Long id);
	
	List<Stanje> findAll();
	
	Stanje save(Stanje stanje);
	
	List<Stanje> save(List<Stanje> stanja);
	
	Stanje delete(Long id);

}
