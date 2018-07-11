package poslovna.informatika.poslovna.controller;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poslovna.informatika.poslovna.converters.PrometniDokumentDTOtoPrometniDokument;
import poslovna.informatika.poslovna.dto.PrometniDokumentDTO;

import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.PrometniDokument;
import poslovna.informatika.poslovna.model.StatusDokumenta;
import poslovna.informatika.poslovna.model.StavkaDokumenta;
import poslovna.informatika.poslovna.service.MagacinService;
import poslovna.informatika.poslovna.service.PoslovniPartnerService;
import poslovna.informatika.poslovna.service.PrometniDokumentService;
import poslovna.informatika.poslovna.service.StavkaDokumentaService;
import poslovna.informatika.poslovna.model.*;
import poslovna.informatika.poslovna.service.*;
import poslovna.informatika.poslovna.util.VrstaPrometaVrstaPrDokumentaMapper;


@Controller
@RequestMapping("/prometniDokument")
public class PrometniDokumentController {

	@Autowired
	private PrometniDokumentDTOtoPrometniDokument prometniDokumentConverter;
	
	@Autowired
	private PrometniDokumentService prometniDokumentService;
	
	@Autowired
	private StavkaDokumentaService stavkaDokumentaService;
	
	@Autowired
	private MagacinService magacinService;
	
	@Autowired
	private PoslovniPartnerService poslovniPartner;

	@Autowired
	private RobnaKarticaService robnaKarticaService;

	@Autowired
	private AnalitikaMagKarticeService analitikaMagKarticeService;

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<PrometniDokument> save(@RequestBody PrometniDokumentDTO dokument,HttpServletRequest request){
		
		
		PrometniDokument prometniDokument=prometniDokumentConverter.convert(dokument);
		Calendar cal = Calendar.getInstance();
		prometniDokument.setDatumForimranja(cal.getTime());
		prometniDokument.setStatus(StatusDokumenta.F);
		prometniDokument.setMagacin(magacinService.findOne(dokument.getMagacin()));
		prometniDokument.setRedniBr(prometniDokumentService.findAll(prometniDokument.getMagacin()).size()+1);
		prometniDokument=prometniDokumentService.save(prometniDokument);
		for(StavkaDokumenta stavka:prometniDokument.getStavkeDokumenta()){
			stavkaDokumentaService.update(prometniDokument, stavka.getId());
		}
		return new ResponseEntity<PrometniDokument>(prometniDokument,HttpStatus.OK);
	}
	@RequestMapping(value="/all/{id}",method=RequestMethod.GET)
	public String allInMagacin(HttpServletRequest request,@PathVariable ("id") Long id){
		Magacin magacin=magacinService.findOne(id);
		request.getSession().setAttribute("magacin", magacin);
		List<PrometniDokument> d=prometniDokumentService.findAll(magacin);
		request.getSession().setAttribute("prometniDokumenti",d );
		return "forward:/prometnaDokumenta.jsp";
	}
	@RequestMapping(value="/allDokumenti",method=RequestMethod.GET)
	public String all(HttpServletRequest request){
		List<PrometniDokument> d=prometniDokumentService.findAll();
		request.getSession().setAttribute("prometniDokumenti",d );
		return "forward:/prometnaDokumenta.jsp";
	}
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public ResponseEntity<List<PrometniDokument>>search(@RequestBody PrometniDokumentDTO dokument){
		PrometniDokument d=prometniDokumentConverter.convert(dokument);
		List<PrometniDokument> search=prometniDokumentService.findByStatusAndVrstaAndMagacinAndPoslovniPartner(d.getStatus(), d.getVrsta(),magacinService.findById(d.getMagacin().getId()),poslovniPartner.findByPib(dokument.getPoslovniPartner()));
		return new ResponseEntity<List<PrometniDokument>>(search,HttpStatus.OK);
	}
	//(ukupna vrednost + količina ulaza * nabavna cena) / (ukupna količina + količina ulaza)

	@RequestMapping(value="/{id}/proknjizi", method=RequestMethod.POST)
	public ResponseEntity proknjizi(@PathVariable("id") Long id) {
		PrometniDokument prometniDokument = prometniDokumentService.findById(id);
		for(StavkaDokumenta stavka : prometniDokument.getStavkeDokumenta()) {
			RobnaKartica robnaKartica = robnaKarticaService.findByMagacinAndRoba(prometniDokument.getMagacin(), stavka.getRoba());
			AnalitikaMagKartice analitikaMagKartice = new AnalitikaMagKartice();
			analitikaMagKartice.setRobnaKartica(robnaKartica);
			float prosecna=(robnaKartica.getUkupnaVr()+stavka.getKolicina()*stavka.getCena())/(robnaKartica.getUkupnaKol()+stavka.getKolicina());
			stavka.setCena(prosecna);
			stavka.setVrednost(prosecna*stavka.getKolicina());
			stavka=stavkaDokumentaService.save(stavka);
			analitikaMagKartice.setStavkaDokumenta(stavka);
			analitikaMagKartice.setVrstaPrometa(VrstaPrometaVrstaPrDokumentaMapper.mapVrstaPrometaToVrstaPrDokumenta(prometniDokument.getVrsta()));
			if(prometniDokument.getVrsta().equals(VrstaPrDokumenta.OT)) {
				analitikaMagKartice.setSmerPrometa(SmerPrometa.I);
			} else {
				analitikaMagKartice.setSmerPrometa(SmerPrometa.U);
			}
			robnaKartica.obradiTransfer(stavka.getKolicina(), stavka.getVrednost(), stavka.getCena(), prometniDokument.getVrsta());
			analitikaMagKartice.setUkupnaVr(robnaKartica.getUkupnaVr());
			analitikaMagKartice.setUkupnaKol(robnaKartica.getUkupnaKol());
			analitikaMagKarticeService.save(analitikaMagKartice);
			robnaKartica.getAnalitike().add(analitikaMagKartice);
			robnaKarticaService.save(robnaKartica);
		}
		prometniDokument.setDatumKnjizenja(new Date());
		prometniDokument.setStatus(StatusDokumenta.P);
		prometniDokumentService.save(prometniDokument);
		return new ResponseEntity(HttpStatus.OK);
	}
}
