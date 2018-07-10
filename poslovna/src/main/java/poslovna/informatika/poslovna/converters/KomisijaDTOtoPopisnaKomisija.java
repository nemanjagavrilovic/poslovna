package poslovna.informatika.poslovna.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import poslovna.informatika.poslovna.dto.KomisijaDTO;
import poslovna.informatika.poslovna.model.PopisnaKomisija;
import poslovna.informatika.poslovna.model.Radnik;
import poslovna.informatika.poslovna.service.RadniciService;
@Component
public class KomisijaDTOtoPopisnaKomisija implements Converter<KomisijaDTO, PopisnaKomisija> {

	@Autowired
	private RadniciService radniciService;
	@Override
	public PopisnaKomisija convert(KomisijaDTO source) {
		if (source == null)
			return null;
		
		PopisnaKomisija komisija = new PopisnaKomisija();
		List<Radnik> radnici = new ArrayList<Radnik>();
		
		for(Long id : source.getKomisija()) {
			radnici.add(radniciService.findById(id));
		}
		komisija.setKomisija(radnici);
		return komisija;
	}

}
