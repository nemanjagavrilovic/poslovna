package poslovna.informatika.poslovna.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import poslovna.informatika.poslovna.model.AnalitikaMagKartice;
import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.Roba;
import poslovna.informatika.poslovna.model.RobnaKartica;
import poslovna.informatika.poslovna.model.SmerPrometa;
import poslovna.informatika.poslovna.model.StavkaDokumenta;
import poslovna.informatika.poslovna.model.VrstaPrometa;
import poslovna.informatika.poslovna.service.*;
import poslovna.informatika.poslovna.validation.RobnaKarticaValidator;
import poslovna.informatika.poslovna.validation.Validator;

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
    private PoslovnaGodinaService poslovnaGodinaService;
	
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
	public ResponseEntity create(@RequestBody RobnaKartica robnaKartica) {
		Magacin magacin = magacinService.findOne(robnaKartica.getMagacin().getId());
		Roba roba = robaServis.findOne(robnaKartica.getRoba().getId());
		
		if(robnaKarticaService.findByMagacinAndRobaAndPoslovnaGodina(magacin, roba,poslovnaGodinaService.findAkivna(true)) != null) {
		    return new ResponseEntity("Vec postoji robna kartica za robu u izabranom magacinu.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        robnaKartica.setPoslovnaGodina(poslovnaGodinaService.findActive(true));
		robnaKartica.setMagacin(magacin);
		robnaKartica.setRoba(roba);
        Validator robnaKarticaValidator = new RobnaKarticaValidator(robnaKartica);
		if(!robnaKarticaValidator.test()) {
		    return new ResponseEntity(robnaKarticaValidator.getResults(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        RobnaKartica karticaSaIstomRobom =robnaKarticaService.findByMagacinAndRoba(magacin, roba);
		AnalitikaMagKartice analitikaMagKartice = new AnalitikaMagKartice();
		analitikaMagKartice.setSmerPrometa(SmerPrometa.U);
		analitikaMagKartice.setVrstaPrometa(VrstaPrometa.PS);
		StavkaDokumenta stavka=new StavkaDokumenta();
		if(karticaSaIstomRobom!=null && !karticaSaIstomRobom.getPoslovnaGodina().getGodina().equals(poslovnaGodinaService.findAkivna(true))) {
			stavka.setVrednost(karticaSaIstomRobom.getUkupnaVr());
			stavka.setKolicina(karticaSaIstomRobom.getUkupnaKol());
			stavka=stavkaDokumentaService.save(stavka);
			analitikaMagKartice.setUkupnaKol(karticaSaIstomRobom.getUkupnaKol());
			analitikaMagKartice.setUkupnaVr(karticaSaIstomRobom.getUkupnaVr());
			analitikaMagKartice.setStavkaDokumenta(stavka);
			robnaKartica.setPocetnoStanjeKol(karticaSaIstomRobom.getUkupnaKol());
			robnaKartica.setPocetnoStanjeVr(karticaSaIstomRobom.getUkupnaVr());
			robnaKartica.setCena(karticaSaIstomRobom.getCena());
			robnaKartica.setPoslovnaGodina(poslovnaGodinaService.findAkivna(true));
			robnaKartica=robnaKarticaService.save(robnaKartica);
			analitikaMagKartice.setRobnaKartica(robnaKartica);
			analitikaMagKartice.setRbr(1);
			analitikaMagKarticeService.save(analitikaMagKartice);
			robnaKartica.setAnalitike(new ArrayList<>());
			robnaKartica.addAnalitika(analitikaMagKartice);
			robnaKartica.setUkupnaKol(karticaSaIstomRobom.getUkupnaKol());
			robnaKartica.setUkupnaVr(karticaSaIstomRobom.getUkupnaVr());
			robnaKartica=robnaKarticaService.save(robnaKartica);
			magacin.getRobneKartice().add(robnaKartica);
			magacinService.save(magacin);
			return new ResponseEntity<>("Kreirana je nova kartica za aktivnu godinu, pocetne vrednosti su uzete sa kartice iz prosle godine.", HttpStatus.OK);
		}
		stavka.setVrednost(robnaKartica.getPocetnoStanjeVr());
		stavka.setKolicina(robnaKartica.getPocetnoStanjeKol());
		stavka=stavkaDokumentaService.save(stavka);
		analitikaMagKartice.setUkupnaKol(robnaKartica.getPocetnoStanjeKol());
		analitikaMagKartice.setUkupnaVr(robnaKartica.getPocetnoStanjeVr());
		analitikaMagKartice.setStavkaDokumenta(stavka);
		robnaKartica.setPoslovnaGodina(poslovnaGodinaService.findAkivna(true));
		robnaKartica=robnaKarticaService.save(robnaKartica);
		analitikaMagKartice.setRobnaKartica(robnaKartica);
		analitikaMagKartice.setRbr(1);
		analitikaMagKarticeService.save(analitikaMagKartice);
		robnaKartica.setAnalitike(new ArrayList<>());
		robnaKartica.addAnalitika(analitikaMagKartice);
		robnaKartica.setUkupnaKol(robnaKartica.getPocetnoStanjeKol());
		robnaKartica.setUkupnaVr(robnaKartica.getPocetnoStanjeVr());
		robnaKartica=robnaKarticaService.save(robnaKartica);
		magacin.getRobneKartice().add(robnaKartica);
		magacinService.save(magacin);
		return new ResponseEntity<>("Uspesno kreirana kartica!", HttpStatus.OK);
	}

	@RequestMapping(value="/{id}/analitika", method=RequestMethod.GET)
	public String analitike(@PathVariable("id") Long id, HttpServletRequest request) {
		RobnaKartica robnaKartica = robnaKarticaService.findById(id);
		List<AnalitikaMagKartice> analitikeSortirane = analitikaMagKarticeService.findByRobnaKarticaOrderByRbrAsc(robnaKartica);
		request.setAttribute("robnaKartica", robnaKartica);
		request.setAttribute("analitikeSortirane", analitikeSortirane);
		return "forward:/analitikaRobneKartice.jsp";
	}
	@RequestMapping(value="/allFromStavka/{id}",method=RequestMethod.GET)
	public ResponseEntity<List<RobnaKartica>> robneKarticeFromStavka(@PathVariable ("id") Long id,HttpServletRequest request){
		
		List<RobnaKartica> robneKartice=robnaKarticaService.findByMagacin(prometniDokumentService.findById(id).getMagacin().getId());
		return new ResponseEntity<List<RobnaKartica>>(robneKartice,HttpStatus.OK);
	}
	@RequestMapping(value="/{id}/nivelacija", method=RequestMethod.GET)
	public ResponseEntity nivelacija(@PathVariable("id") Long id, HttpServletRequest request) {
		RobnaKartica robnaKartica = robnaKarticaService.findById(id);
		AnalitikaMagKartice analitikaMagKartice = new AnalitikaMagKartice();
		StavkaDokumenta stavka=new StavkaDokumenta();
		stavka.setVrednost(robnaKartica.nivelacija());
		stavka.setKolicina(0);
		stavka.setCena(0);
		if(stavka.getVrednost()>0){
			analitikaMagKartice.setSmerPrometa(SmerPrometa.U);
			
		}else
			analitikaMagKartice.setSmerPrometa(SmerPrometa.I);
		
		analitikaMagKartice.setVrstaPrometa(VrstaPrometa.NI);
		analitikaMagKartice.setRbr(robnaKartica.getAnalitike().size() + 1);
		analitikaMagKartice.setRobnaKartica(robnaKartica);
		stavka=stavkaDokumentaService.save(stavka);
		analitikaMagKartice.setStavkaDokumenta(stavka);
		analitikaMagKartice.setUkupnaKol(robnaKartica.getUkupnaKol());
		analitikaMagKartice.setUkupnaVr(robnaKartica.getUkupnaVr());
		analitikaMagKartice.setRbr(robnaKartica.getAnalitike().size()+1);
		analitikaMagKarticeService.save(analitikaMagKartice);
		robnaKartica.addAnalitika(analitikaMagKartice);
		robnaKarticaService.save(robnaKartica);

		return new ResponseEntity(HttpStatus.OK);

	}
	
	@RequestMapping(value="/{id}/izvestaj", method=RequestMethod.POST)
	public ResponseEntity<String> izvestaj(@PathVariable("id") Long id) {
		try {
			Connection conn;
			conn =
				       DriverManager.getConnection("jdbc:mysql://localhost:3306/poslovna?useSSL=false&" +
				                                   "user=root&password=malizvornik95");
			HashMap map = new HashMap();
			map.put("idRobneKartice", id);
            JasperReport jasReport = (JasperReport) JRLoader.loadObjectFromFile("C:/Users/Nemanja/git/poslovna/poslovna/src/main/resources/magacinska kartica sa analitikom.jasper");
            JasperPrint jasPrint = JasperFillManager.fillReport(jasReport, map, conn);
            File pdf = File.createTempFile("output.", ".pdf");
			JasperExportManager.exportReportToPdfStream(jasPrint, new FileOutputStream(pdf));
			System.out.println("Temp file : " + pdf.getAbsolutePath());
			
		}catch (Exception ex) {
				ex.printStackTrace();
			}
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	private ResponseEntity edit(@PathVariable("id") Long id, @RequestBody RobnaKartica newRobnaKartica) {
		RobnaKartica robnaKartica = robnaKarticaService.findById(id);
		robnaKartica.setCena(newRobnaKartica.getCena());
		robnaKarticaService.save(robnaKartica);
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value="/search", method = RequestMethod.GET)
	private String search(@RequestParam("magacin") String magacinIme, @RequestParam("roba") String robaIme, HttpServletRequest request) {
		List<RobnaKartica> robneKartice = robnaKarticaService.findByMagacinNameAndRobaName(magacinIme, robaIme);
		request.setAttribute("robneKartice", robneKartice);
		return "forward:/robne_kartice.jsp";
	}
}
