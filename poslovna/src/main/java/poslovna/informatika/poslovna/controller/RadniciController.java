package poslovna.informatika.poslovna.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.PoslovniPartner;
import poslovna.informatika.poslovna.model.Radnik;
import poslovna.informatika.poslovna.service.MagacinService;
import poslovna.informatika.poslovna.service.RadniciService;

@Controller
@RequestMapping(value = "/api/radnici")
public class RadniciController {

	@Autowired
	private RadniciService radniciService;
	
	@Autowired
	private MagacinService  magacinService;
	
	@RequestMapping("/all")
	public ResponseEntity<List<Radnik>> radnici(){
		
		return new ResponseEntity<List<Radnik>>(radniciService.findAll(),HttpStatus.OK);
	}
	
	@RequestMapping("/unemployed")
	public ResponseEntity<List<Radnik>> unemployed(){
		
		return new ResponseEntity<List<Radnik>>(radniciService.findByMagacin(null),HttpStatus.OK);
	}
	
	@RequestMapping(value="getMagacini", method = RequestMethod.GET)
	public ResponseEntity<List<Magacin>> getMagacini() {

		List<Magacin> magacin = magacinService.findAll();

		return new ResponseEntity<>(magacin, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Radnik> addRadnik(@RequestBody Radnik radnik){
			
		Radnik novRadnik = radniciService.save(radnik);
		return new ResponseEntity<>(novRadnik, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Radnik> delete(@PathVariable Long id) {
		Radnik deleted = radniciService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
	@RequestMapping(value="getRadnici", method = RequestMethod.GET)
	public ResponseEntity<List<Radnik>> getRadnici() {

		List<Radnik> radnik = radniciService.findAll();

		return new ResponseEntity<>(radnik, HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "search")
	public ResponseEntity<List<Radnik>> searchRadnik(@RequestBody Radnik radnik) {
		List<Radnik> radnici= radniciService.findAll();
		
		if(!radnik.getIme().equals(""))
			radnici= radnici.stream().filter(e->e.getIme().toUpperCase().contains(radnik.getIme().toUpperCase())).collect(Collectors.toList());
		
		if(radnik.getPrezime() != null)
			radnici= radnici.stream().filter(e->e.getPrezime().toUpperCase().contains(radnik.getPrezime().toUpperCase())).collect(Collectors.toList());
		if(!radnik.getAdresa().equals("")){
			radnici= radnici.stream().filter(e->e.getAdresa().toUpperCase().contains(radnik.getAdresa().toUpperCase())).collect(Collectors.toList());
		}
		if(radnik.getMagacin() != null){
			radnici= radnici.stream().filter(e->e.getMagacin().getNaziv().equals(radnik.getMagacin().getNaziv())).collect(Collectors.toList());
		}
		
		return new ResponseEntity<>(radnici, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "edit")
	public ResponseEntity<List<Radnik>> editRadnika(@RequestBody Radnik radnikEdit) {
		Radnik radnik = radniciService.findById(radnikEdit.getId());
		radnik.setId(radnikEdit.getId());
		radnik.setIme(radnikEdit.getIme());
		radnik.setPrezime(radnikEdit.getPrezime());
		radnik.setAdresa(radnikEdit.getAdresa());
		radnik.setMagacin(radnikEdit.getMagacin());
		

		radniciService.save(radnik);	
		
		return new ResponseEntity<>(radniciService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Radnik> getRadnik(@PathVariable Long id) {
		Radnik radnik = radniciService.findById(id);
		if (radnik == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(radnik, HttpStatus.OK);
	}
	
}

