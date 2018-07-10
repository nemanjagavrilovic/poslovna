package poslovna.informatika.poslovna.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import poslovna.informatika.poslovna.dto.StavkaDokumentaDTO;
import poslovna.informatika.poslovna.model.StavkaDokumenta;
import poslovna.informatika.poslovna.service.JpaRobaServis;

@Component
public class StavkaDokumentaDTOtoStavkaDokumenta implements Converter<StavkaDokumentaDTO,StavkaDokumenta> {

	@Autowired
	private JpaRobaServis robaService;
	@Override
	public StavkaDokumenta convert(StavkaDokumentaDTO source) {
		// TODO Auto-generated method stub
		if(source==null)
			return null;
		StavkaDokumenta stavka=new StavkaDokumenta();
		stavka.setCena(source.getCena());
		stavka.setKolicina(source.getKolicina());
		stavka.setVrednost(source.getVrednost());
		stavka.setRoba(robaService.findOne(source.getRoba()));
		return stavka;
	}

}
