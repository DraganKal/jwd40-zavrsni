package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Sprint;
import jwd.wafepa.web.dto.SprintDTO;
// Konvertor koji konvertuje Sprint u SprintDTO
@Component
public class SprintToDTO implements Converter<Sprint, SprintDTO>{
	
	@Override
	public SprintDTO convert(Sprint sprint) {
		SprintDTO retValue = new SprintDTO();
		
		retValue.setId(sprint.getId());
		retValue.setIme(sprint.getIme());
		retValue.setUkupnoBodova(sprint.getUkupnoBodova());
		
		return retValue;
	}

	public List<SprintDTO> convert(List<Sprint> dtos){
		List<SprintDTO> ret = new ArrayList<>();
		
		for(Sprint it : dtos){
			ret.add(convert(it));
		}
		
		return ret;
	}

}
