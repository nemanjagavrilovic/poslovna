package poslovna.informatika.poslovna.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import poslovna.informatika.poslovna.dto.StavkaPopisaDTO;
import poslovna.informatika.poslovna.model.RobnaKartica;
import poslovna.informatika.poslovna.model.StavkaPopisa;
import poslovna.informatika.poslovna.service.RobnaKarticaService;
@Component
public class StavkaPopisaDTOtoStavkaPopisa implements Converter<StavkaPopisaDTO,List<StavkaPopisa>>{
	@Autowired
	private RobnaKarticaService robnaKarticaService;
	@Override
	public List<StavkaPopisa> convert(StavkaPopisaDTO source) {
		
		List<RobnaKartica> kartice = robnaKarticaService.findByMagacin(source.getMagacin());
		List<StavkaPopisa> stavke = new ArrayList<StavkaPopisa>();
		for(int i = 0;i<source.getStavke().size();i++) {
			StavkaPopisa sp = new StavkaPopisa();
			sp.setKolicinaPoPopisu(source.getVrednosti().get(i));
			for(RobnaKartica rk : kartice) {
				if(rk.getRoba().getId() == source.getStavke().get(i)){
					sp.setKolicinaPoKartici(rk.getUkupnaKol());
				}
			}
			stavke.add(sp);
		}
		return stavke;
	}

}
