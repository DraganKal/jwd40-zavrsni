package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Sprint;
import jwd.wafepa.model.Stanje;
import jwd.wafepa.model.Zadatak;
import jwd.wafepa.service.SprintService;
import jwd.wafepa.service.StanjeService;
import jwd.wafepa.service.ZadatakService;
import jwd.wafepa.web.dto.ZadatakDTO;
// Konvertor koji konvertuje ZadatakDTO u Zadatak
@Component
public class DTOtoZadatak implements Converter<ZadatakDTO, Zadatak>{
	
	@Autowired
	private ZadatakService zadatakService;
	@Autowired
	private SprintService sprintService;
	@Autowired
	private StanjeService stanjeService;
	
	@Override
	public Zadatak convert(ZadatakDTO dto) {
		
		Sprint sprint = sprintService.findOne(dto.getSprintId());
		Stanje stanje = null;
		if(dto.getStanjeId() != null) {
		stanje = stanjeService.findOne(dto.getStanjeId());
		}
	
		if(sprint != null) {
			
			Zadatak zadatak = null;
			
			if(dto.getId() != null) {
				zadatak = zadatakService.findOne(dto.getId());
			}
			else {
				zadatak = new Zadatak();
			}
			zadatak.setId(dto.getId());
			zadatak.setIme(dto.getIme());
			zadatak.setZaduzeni(dto.getZaduzeni());
			zadatak.setBodovi(dto.getBodovi());
			zadatak.setSprint(sprint);
			if(stanje != null) {
			zadatak.setStanje(stanje);
			}else {
				zadatak.setStanje(stanjeService.findOne(1l));
			}
			
			
			return zadatak;
		}else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	public List<Zadatak> convert(List<ZadatakDTO> dtos){
		List<Zadatak> ret = new ArrayList<>();
		
		for(ZadatakDTO it : dtos){
			ret.add(convert(it));
		}
		
		return ret;
	}

}
