package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Stanje;
import jwd.wafepa.web.dto.StanjeDTO;
// Konvertor koji konvertuje Stanje u StanjeDTO
@Component
public class StanjeToDTO implements Converter<Stanje, StanjeDTO>{
	
	@Override
	public StanjeDTO convert(Stanje stanje) {
		
		StanjeDTO retValue = new StanjeDTO();
		
		retValue.setId(stanje.getId());
		retValue.setIme(stanje.getIme());
		
		return retValue;
	}

	public List<StanjeDTO> convert(List<Stanje> dtos){
		List<StanjeDTO> ret = new ArrayList<>();
		
		for(Stanje it : dtos){
			ret.add(convert(it));
		}
		
		return ret;
	}


}
