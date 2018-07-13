package poslovna.informatika.poslovna.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import poslovna.informatika.poslovna.model.Mesto;
import poslovna.informatika.poslovna.model.PoslovniPartner;
import poslovna.informatika.poslovna.service.MestoService;
import poslovna.informatika.poslovna.service.PoslovniPartnerService;

@RestController
@RequestMapping(value = "/api/mesto")
public class MestoController {
	
	@Autowired
	private MestoService mestoService;
	
	@Autowired
	private PoslovniPartnerService poslovniPartnerService;
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Mesto> addMesto(@RequestBody Mesto mesto){
			
		Mesto novoMesto = mestoService.save(mesto);
		return new ResponseEntity<>(novoMesto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Mesto> delete(@PathVariable Long id) {
		Mesto deleted = mestoService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
	@RequestMapping(value="getMesto", method = RequestMethod.GET)
	public ResponseEntity<List<Mesto>> getMesto() {

		List<Mesto> mesto = mestoService.findAll();

		return new ResponseEntity<>(mesto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Mesto> getMesto(@PathVariable Long id) {
		Mesto mesto = mestoService.findOne(id);
		if (mesto == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(mesto, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "search")
	public ResponseEntity<List<Mesto>> searchMesto(@RequestBody Mesto mesto) {
		List<Mesto> m  =new ArrayList<Mesto>();
		
		if(mesto.getNaziv() !=("") &&  mesto.getPtt() ==0){
			m = mestoService.findByNazivContainingIgnoreCase(mesto.getNaziv());
		}else if(mesto.getNaziv() ==("") &&  mesto.getPtt() !=0){

			m =(mestoService.findByPtt(mesto.getPtt()));
		}else if(mesto.getNaziv() !=("") &&  mesto.getPtt() !=0){
			m=mestoService.findByNazivAndPtt(mesto.getNaziv(),mesto.getPtt());
		}else{
			m=mestoService.findAll();
		}

		return new ResponseEntity<>(m, HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "edit")
	public ResponseEntity<List<Mesto>> editMesto(@RequestBody Mesto mestoEdit) {
		Mesto mesto = mestoService.findOne(mestoEdit.getId());
		mesto.setId(mestoEdit.getId());
		mesto.setNaziv(mestoEdit.getNaziv());
		mesto.setPtt(mestoEdit.getPtt());

		mestoService.save(mesto);	
		
		return new ResponseEntity<>(mestoService.findAll(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="getPoslovniPartnerMesto", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<List<PoslovniPartner>> getPoslovniPartnerMesto(@RequestBody Mesto mesto){
		List<PoslovniPartner> poslovniPartneri = poslovniPartnerService.findAll();
		List<PoslovniPartner> temp = new ArrayList<PoslovniPartner>();
		for(int i = 0 ; i < poslovniPartneri.size(); i++){
			if (mesto.getNaziv().equals(poslovniPartneri.get(i).getMesto().getNaziv()))
				temp.add(poslovniPartneri.get(i));
		}
		
		return new ResponseEntity<>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(value="addPoslovniPartner", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<PoslovniPartner> addPoslovniPartner(@RequestBody PoslovniPartner partner){
			
		PoslovniPartner novpartner = poslovniPartnerService.save(partner);
		return new ResponseEntity<>(novpartner, HttpStatus.OK);
	}
	

}
