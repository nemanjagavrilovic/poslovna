package poslovna.informatika.poslovna.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import poslovna.informatika.poslovna.dto.StavkaPopisaDTO;
import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.PoslovnaGodina;
import poslovna.informatika.poslovna.model.RobnaKartica;
import poslovna.informatika.poslovna.model.StavkaPopisa;
import poslovna.informatika.poslovna.service.MagacinService;
import poslovna.informatika.poslovna.service.PoslovnaGodinaService;
import poslovna.informatika.poslovna.service.RobnaKarticaService;
@Component
public class StavkaPopisaDTOtoStavkaPopisa implements Converter<StavkaPopisaDTO,List<StavkaPopisa>>{
	@Autowired
	private RobnaKarticaService robnaKarticaService;
	@Autowired
	private PoslovnaGodinaService poslovnaGodinaService;
	@Autowired
	private MagacinService magacinService;
	@Override
	public List<StavkaPopisa> convert(StavkaPopisaDTO source) {
		PoslovnaGodina pg = poslovnaGodinaService.findActive(true);
		Magacin magacin = magacinService.findById(source.getMagacin());
		List<RobnaKartica> kartice = robnaKarticaService.findByMagacinAndPoslovnaGodina(magacin,pg);
		List<StavkaPopisa> stavke = new ArrayList<StavkaPopisa>();
		for(int i = 0;i<source.getStavke().size();i++) {
			StavkaPopisa sp = new StavkaPopisa();
			sp.setKolicinaPoPopisu(source.getVrednosti().get(i));
			for(RobnaKartica rk : kartice) {
				if(rk.getRoba().getId() == source.getStavke().get(i)){
					sp.setKolicinaPoKartici(rk.getUkupnaKol());
					sp.setRoba(rk.getRoba());
				}
			}
			stavke.add(sp);
		}
		return stavke;
	}

}
