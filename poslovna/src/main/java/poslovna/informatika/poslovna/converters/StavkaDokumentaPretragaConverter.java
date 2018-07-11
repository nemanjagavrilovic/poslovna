package poslovna.informatika.poslovna.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import poslovna.informatika.poslovna.dto.StavkaDokumentaPretraga;
import poslovna.informatika.poslovna.model.StavkaDokumenta;
import poslovna.informatika.poslovna.service.JpaRobaServis;

@Component
public class StavkaDokumentaPretragaConverter implements Converter<StavkaDokumentaPretraga,StavkaDokumenta>  {

	@Autowired
	private JpaRobaServis robaService;
	@Override
	public StavkaDokumenta convert(StavkaDokumentaPretraga source) {
		// TODO Auto-generated method stub
		if(source==null)
			return null;
		StavkaDokumenta stavka=new StavkaDokumenta();
		stavka.setCena(source.getCena());
		stavka.setKolicina(source.getKolicina());
		stavka.setVrednost(source.getVrednost());
		stavka.setRoba(robaService.findByNazivContainingIgnoreCase(source.getRoba()).get(0));
		return stavka;
	}

}
