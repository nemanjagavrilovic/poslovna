package poslovna.informatika.poslovna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import poslovna.informatika.poslovna.converters.PopisniDokumentDTOtoPopisniDokument;
import poslovna.informatika.poslovna.dto.PopisniDokumentDTO;
import poslovna.informatika.poslovna.model.AnalitikaMagKartice;
import poslovna.informatika.poslovna.model.PopisnaKomisija;
import poslovna.informatika.poslovna.model.PopisniDokument;
import poslovna.informatika.poslovna.model.PoslovnaGodina;
import poslovna.informatika.poslovna.model.RobnaKartica;
import poslovna.informatika.poslovna.model.SmerPrometa;
import poslovna.informatika.poslovna.model.StavkaPopisa;
import poslovna.informatika.poslovna.model.VrstaPrometa;
import poslovna.informatika.poslovna.service.AnalitikaMagKarticeService;
import poslovna.informatika.poslovna.service.PopisnaKomisijaService;
import poslovna.informatika.poslovna.service.PopisniDokumentService;
import poslovna.informatika.poslovna.service.PoslovnaGodinaService;
import poslovna.informatika.poslovna.service.RobnaKarticaService;

@RestController
@RequestMapping(value = "/popisniDokument")
public class PopisniDokumentKontroler {

	@Autowired
	private PopisniDokumentService popisniDokumentService;
	@Autowired
	private PopisnaKomisijaService popisnaKomisijaService;
	@Autowired
	private PoslovnaGodinaService poslovnaGodinaService;
	@Autowired
	private RobnaKarticaService robnaKarticaService;
	@Autowired
	private AnalitikaMagKarticeService analitikaMagKarticeService;
	@Autowired
	private PopisniDokumentDTOtoPopisniDokument dokumentDTOtopopisniDokument;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<PopisniDokument> save(@RequestBody PopisniDokumentDTO dokumentDTO) {

		PopisniDokument popisniDokument = dokumentDTOtopopisniDokument.convert(dokumentDTO);
		PopisniDokument retDokument = popisniDokumentService.save(popisniDokument);

		PopisnaKomisija komisija = popisnaKomisijaService.findById(popisniDokument.getKomisija().getId());

		komisija.setDokument(retDokument);

		popisnaKomisijaService.save(komisija);

		PoslovnaGodina pg = poslovnaGodinaService.findActive(false);

		retDokument.setPoslovnaGodina(pg);
		popisniDokumentService.save(retDokument);

		doUpdate(retDokument, pg);
		return new ResponseEntity<PopisniDokument>(popisniDokument, HttpStatus.OK);
	}

	private void doUpdate(PopisniDokument retDokument, PoslovnaGodina pg) {
		List<RobnaKartica> kartice = robnaKarticaService.findByMagacinAndPoslovnaGodina(retDokument.getMagacin(), pg);

		for (StavkaPopisa sp : retDokument.getStavke()) {
			System.out.println("stavka: " + sp.getRoba().getId());
			for(RobnaKartica rk : kartice) {
				System.out.println("kartica: " + rk.getRoba().getId());
				if(sp.getRoba().getId()==rk.getRoba().getId()){
					if(sp.getKolicinaPoKartici() != sp.getKolicinaPoPopisu()) {
						rk.obradiTransfer(sp.getKolicinaPoKartici(), 0.0f, rk.getCena(), null);
						RobnaKartica retKartica = robnaKarticaService.save(rk);
						
						AnalitikaMagKartice analitikaMagKartice = new AnalitikaMagKartice();
						analitikaMagKartice.setRobnaKartica(retKartica);
						analitikaMagKartice.setVrstaPrometa(VrstaPrometa.KOR);
						if(sp.getKolicinaPoKartici() < rk.getUkupnaKol()) {
							analitikaMagKartice.setSmerPrometa(SmerPrometa.U);
						} else {
							analitikaMagKartice.setSmerPrometa(SmerPrometa.I);
						}
						
						AnalitikaMagKartice retAnalitika = analitikaMagKarticeService.save(analitikaMagKartice);
						
						
						retKartica.addAnalitika(retAnalitika);
						robnaKarticaService.save(retKartica);
					}
				}
			}
		}
	}

}
