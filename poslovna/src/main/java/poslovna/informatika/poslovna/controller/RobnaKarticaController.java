package poslovna.informatika.poslovna.controller;

import java.util.ArrayList;
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

import poslovna.informatika.poslovna.model.AnalitikaMagKartice;
import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.Roba;
import poslovna.informatika.poslovna.model.RobnaKartica;
import poslovna.informatika.poslovna.model.SmerPrometa;
import poslovna.informatika.poslovna.model.StavkaDokumenta;
import poslovna.informatika.poslovna.model.VrstaPrometa;
import poslovna.informatika.poslovna.service.AnalitikaMagKarticeService;
import poslovna.informatika.poslovna.service.MagacinService;
import poslovna.informatika.poslovna.service.PrometniDokumentService;
import poslovna.informatika.poslovna.service.RobaServis;
import poslovna.informatika.poslovna.service.RobnaKarticaService;
import poslovna.informatika.poslovna.service.StavkaDokumentaService;

@Controller
@RequestMapping("/robnaKartica")
public class RobnaKarticaController {
	
	@Autowired
	private RobnaKarticaService robnaKarticaService;

	@Autowired
	private MagacinService magacinService;

	@Autowired
	private RobaServis robaServis;

	@Autowired 
	private PrometniDokumentService prometniDokumentService;
	

	@Autowired
	private StavkaDokumentaService stavkaDokumentaService;
	
	@Autowired
	private AnalitikaMagKarticeService analitikaMagKarticeService;
	@RequestMapping(value="/all/{id}",method=RequestMethod.GET)
	public ResponseEntity<List<RobnaKartica>> robneKartice(@PathVariable ("id") Long id,HttpServletRequest request){
		List<RobnaKartica> robneKartice=robnaKarticaService.findByMagacin(id);
		return new ResponseEntity<List<RobnaKartica>>(robneKartice,HttpStatus.OK);
	}

	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String robneKarticeIndex(HttpServletRequest request) {
		List<RobnaKartica> robneKartice = robnaKarticaService.findAll();
		request.setAttribute("robneKartice", robneKartice);
		return "forward:/robne_kartice.jsp";
	}

	@RequestMapping(value="/index/{magacin_id}", method = RequestMethod.GET)
	public String robneKarticePoMagacinuIndex(HttpServletRequest request, @PathVariable("magacin_id") Long magId) {
		List<RobnaKartica> robneKartice = robnaKarticaService.findByMagacin(magId);
		Magacin magacin = magacinService.findOne(magId);
		request.setAttribute("robneKartice", robneKartice);
		request.setAttribute("magacin", magacin);
		return "forward:/robne_kartice.jsp";
	}

	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity <RobnaKartica> findById(@PathVariable("id") Long id){
		RobnaKartica robneKartice=robnaKarticaService.findById(id);
		return new ResponseEntity<>(robneKartice,HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<RobnaKartica> create(@RequestBody RobnaKartica robnaKartica) {
		Magacin magacin = magacinService.findOne(robnaKartica.getMagacin().getId());
		Roba roba = robaServis.findOne(robnaKartica.getRoba().getId());
		robnaKartica.setMagacin(magacin);
		robnaKartica.setRoba(roba);
		AnalitikaMagKartice analitikaMagKartice = new AnalitikaMagKartice();
		analitikaMagKartice.setSmerPrometa(SmerPrometa.U);
		analitikaMagKartice.setVrstaPrometa(VrstaPrometa.PS);
		StavkaDokumenta stavka=new StavkaDokumenta();
		stavka.setVrednost(robnaKartica.getPocetnoStanjeVr());
		stavka.setKolicina(robnaKartica.getPocetnoStanjeKol());
		stavka.setCena(0);
		stavka=stavkaDokumentaService.save(stavka);
		analitikaMagKartice.setUkupnaKol(robnaKartica.getPocetnoStanjeKol());
		analitikaMagKartice.setUkupnaVr(robnaKartica.getPocetnoStanjeVr());
		analitikaMagKartice.setStavkaDokumenta(stavka);
		robnaKartica=robnaKarticaService.save(robnaKartica);
		analitikaMagKartice.setRobnaKartica(robnaKartica);
		analitikaMagKarticeService.save(analitikaMagKartice);
		robnaKartica.setAnalitike(new ArrayList<AnalitikaMagKartice>());
		robnaKartica.addAnalitika(analitikaMagKartice);
		robnaKartica.setUkupnaKol(robnaKartica.getPocetnoStanjeKol());
		robnaKartica.setUkupnaVr(robnaKartica.getPocetnoStanjeVr());
		robnaKarticaService.save(robnaKartica);
		return new ResponseEntity<>(robnaKartica, HttpStatus.OK);
	}

	@RequestMapping(value="/{id}/analitika", method=RequestMethod.GET)
	public String analitike(@PathVariable("id") Long id, HttpServletRequest request) {
		RobnaKartica robnaKartica = robnaKarticaService.findById(id);
		request.setAttribute("robnaKartica", robnaKartica);
		return "forward:/analitikaRobneKartice.jsp";
	}
	@RequestMapping(value="/allFromStavka/{id}",method=RequestMethod.GET)
	public ResponseEntity<List<RobnaKartica>> robneKarticeFromStavka(@PathVariable ("id") Long id,HttpServletRequest request){
		
		List<RobnaKartica> robneKartice=robnaKarticaService.findByMagacin(prometniDokumentService.findById(id).getMagacin().getId());
		return new ResponseEntity<List<RobnaKartica>>(robneKartice,HttpStatus.OK);
	}
	@RequestMapping(value="/{id}/nivelacija", method=RequestMethod.GET)
	public String nivelacija(@PathVariable("id") Long id, HttpServletRequest request) {
		RobnaKartica robnaKartica = robnaKarticaService.findById(id);
		AnalitikaMagKartice analitikaMagKartice = new AnalitikaMagKartice();
		analitikaMagKartice.setSmerPrometa(SmerPrometa.U);
		analitikaMagKartice.setVrstaPrometa(VrstaPrometa.NI);
		analitikaMagKartice.setRobnaKartica(robnaKartica);
		StavkaDokumenta stavka=new StavkaDokumenta();
		stavka.setVrednost(robnaKartica.nivelacija());
		stavka.setKolicina(0);
		stavka.setCena(0);
		stavka=stavkaDokumentaService.save(stavka);
		analitikaMagKartice.setStavkaDokumenta(stavka);
		analitikaMagKartice.setUkupnaKol(robnaKartica.getUkupnaKol());
		analitikaMagKartice.setUkupnaVr(robnaKartica.getUkupnaVr());
		analitikaMagKarticeService.save(analitikaMagKartice);
		robnaKartica.addAnalitika(analitikaMagKartice);
		robnaKarticaService.save(robnaKartica);

		return "forward:/analitikaRobneKartice.jsp";
	}
}
