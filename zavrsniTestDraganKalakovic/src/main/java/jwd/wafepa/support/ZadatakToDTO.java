package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Zadatak;
import jwd.wafepa.web.dto.ZadatakDTO;
// Konvertor koji konvertuje Zadatak u ZadatakDTO
@Component
public class ZadatakToDTO implements Converter<Zadatak, ZadatakDTO>{
	
	@Override
	public ZadatakDTO convert(Zadatak zadatak) {
		
		ZadatakDTO retValue = new ZadatakDTO();
		retValue.setBodovi(zadatak.getBodovi());
		retValue.setId(zadatak.getId());
		retValue.setIme(zadatak.getIme());
		retValue.setSprintId(zadatak.getSprint().getId());
		retValue.setSprintNaziv(zadatak.getSprint().getIme());
		retValue.setStanjeId(zadatak.getStanje().getId());
		retValue.setStanjeNaziv(zadatak.getStanje().getIme());
		retValue.setZaduzeni(zadatak.getZaduzeni());
		
		
		
		return retValue;
	}

	public List<ZadatakDTO> convert(List<Zadatak> list){
		List<ZadatakDTO> ret = new ArrayList<>();
		
		for(Zadatak it : list){
			ret.add(convert(it));
		}
		
		return ret;
	}


}
