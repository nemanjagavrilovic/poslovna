package poslovna.informatika.poslovna.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import poslovna.informatika.poslovna.converters.PopisniDokumentDTOtoPopisniDokument;
import poslovna.informatika.poslovna.dto.PopisniDokumentDTO;
import poslovna.informatika.poslovna.model.AnalitikaMagKartice;
import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.PopisnaKomisija;
import poslovna.informatika.poslovna.model.PopisniDokument;
import poslovna.informatika.poslovna.model.PoslovnaGodina;
import poslovna.informatika.poslovna.model.RobnaKartica;
import poslovna.informatika.poslovna.model.SmerPrometa;
import poslovna.informatika.poslovna.model.StavkaDokumenta;
import poslovna.informatika.poslovna.model.StavkaPopisa;
import poslovna.informatika.poslovna.model.VrstaPrometa;
import poslovna.informatika.poslovna.service.AnalitikaMagKarticeService;
import poslovna.informatika.poslovna.service.MagacinService;
import poslovna.informatika.poslovna.service.PopisnaKomisijaService;
import poslovna.informatika.poslovna.service.PopisniDokumentService;
import poslovna.informatika.poslovna.service.PoslovnaGodinaService;
import poslovna.informatika.poslovna.service.RobnaKarticaService;
import poslovna.informatika.poslovna.service.StavkaDokumentaService;

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
	private StavkaDokumentaService stavkaDokumentaService;
	@Autowired
	private MagacinService magacinService;
	@Autowired
	private PopisniDokumentDTOtoPopisniDokument dokumentDTOtopopisniDokument;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<PopisniDokument> save(@RequestBody PopisniDokumentDTO dokumentDTO) {

		PopisniDokument popisniDokument = dokumentDTOtopopisniDokument.convert(dokumentDTO);
		PopisniDokument retDokument = popisniDokumentService.save(popisniDokument);

		PopisnaKomisija komisija = popisnaKomisijaService.findById(popisniDokument.getKomisija().getId());

		komisija.setDokument(retDokument);

		popisnaKomisijaService.save(komisija);

		PoslovnaGodina pg = poslovnaGodinaService.findActive(true);

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
						
						AnalitikaMagKartice analitikaMagKartice = new AnalitikaMagKartice();
						analitikaMagKartice.setVrstaPrometa(VrstaPrometa.KOR);
						analitikaMagKartice.setRbr(rk.getAnalitike().size() + 1);
						
						if(sp.getKolicinaPoPopisu() > rk.getUkupnaKol()) {
							analitikaMagKartice.setSmerPrometa(SmerPrometa.U);
							rk.visak(Math.abs(sp.getKolicinaPoKartici()-sp.getKolicinaPoPopisu()));
						} else {
							analitikaMagKartice.setSmerPrometa(SmerPrometa.I);
							rk.manjak(Math.abs(sp.getKolicinaPoKartici()-sp.getKolicinaPoPopisu()));
						}
						
						StavkaDokumenta stavka=new StavkaDokumenta();
						stavka.setVrednost(Math.abs(sp.getKolicinaPoKartici()-sp.getKolicinaPoPopisu())*rk.getCena());
						stavka.setKolicina(Math.abs(sp.getKolicinaPoKartici()-sp.getKolicinaPoPopisu()));
						stavka.setCena(rk.getCena());
						stavka=stavkaDokumentaService.save(stavka);
						analitikaMagKartice.setStavkaDokumenta(stavka);
						analitikaMagKartice.setUkupnaKol(rk.getUkupnaKol());
						analitikaMagKartice.setUkupnaVr(rk.getUkupnaVr());
						RobnaKartica retKartica = robnaKarticaService.save(rk);
						analitikaMagKartice.setRobnaKartica(retKartica);
						
						AnalitikaMagKartice retAnalitika = analitikaMagKarticeService.save(analitikaMagKartice);
						
						
						retKartica.addAnalitika(retAnalitika);
						robnaKarticaService.save(retKartica);
					}
				}
			}
		}
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<PopisniDokument>> getAll() {

		List<PopisniDokument> dokumenti = popisniDokumentService.findAll();
		return new ResponseEntity<List<PopisniDokument>>(dokumenti, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAll/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<PopisniDokument>> getAllByMagacin(@PathVariable("id") Long id) {
		Magacin magacin = magacinService.findById(id);
		List<PopisniDokument> dokumenti = popisniDokumentService.findByMagacin(magacin);
		return new ResponseEntity<List<PopisniDokument>>(dokumenti, HttpStatus.OK);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<List<PopisniDokument>> search(@RequestBody PopisniDokument pd) {
		
		List<PopisniDokument> dokumenti = popisniDokumentService.findAll();
		if(pd.getDatum() !=null) {
			System.out.println(pd.getDatum());
			dokumenti=dokumenti.stream().filter(e -> e.getDatum().compareTo(pd.getDatum())<=0).collect(Collectors.toList());
		}
		if(pd.getPoslovnaGodine() != null) {
			
				dokumenti=dokumenti.stream().filter(e -> e.getPoslovnaGodine().getGodina().compareTo(pd.getPoslovnaGodine().getGodina())<=0).collect(Collectors.toList());
				
		}
		
		if(pd.getBrojPopisa()!=-1) {
			System.out.println("USAO");
			Long id = new Long(pd.getBrojPopisa());
			Magacin magacin = magacinService.findById(id);
			dokumenti=dokumenti.stream().filter(e -> e.getMagacin().getId().equals(magacin.getId())).collect(Collectors.toList());
		}
		
		return new ResponseEntity<List<PopisniDokument>>(dokumenti, HttpStatus.OK);
	}
}
