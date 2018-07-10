package poslovna.informatika.poslovna.converters;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import poslovna.informatika.poslovna.dto.MagacinDTO;
import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.Radnik;
import poslovna.informatika.poslovna.service.RadniciService;

@Component
public class MagacinDTOtoMagacin implements Converter<MagacinDTO,Magacin>{

	@Autowired
	private RadniciService radniciService;
	@Override
	public Magacin convert(MagacinDTO source) {
		// TODO Auto-generated method stub
		if(source==null)
			return null;
		Magacin magacin=new Magacin();
		magacin.setNaziv(source.getNaziv());
		magacin.setId(source.getId());
		if(source.getRadnici().size()!=0){
			magacin.setRadnici(new ArrayList<Radnik>());
			for(Long id:source.getRadnici()){
				magacin.getRadnici().add(radniciService.findById(id));
			}
		}
		return magacin;
	}

}
