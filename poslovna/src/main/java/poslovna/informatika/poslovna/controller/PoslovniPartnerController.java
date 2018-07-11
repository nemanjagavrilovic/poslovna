
package poslovna.informatika.poslovna.controller;

import java.util.ArrayList;
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

import poslovna.informatika.poslovna.model.Mesto;
import poslovna.informatika.poslovna.model.PoslovniPartner;
import poslovna.informatika.poslovna.service.PoslovniPartnerService;



@RestController
@RequestMapping(value = "/api/poslovniPartner")
public class PoslovniPartnerController {
	
	@Autowired
	private PoslovniPartnerService poslovniPartnerService;
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<PoslovniPartner> addPoslovniPartner(@RequestBody PoslovniPartner partner){
			
		PoslovniPartner novpartner = poslovniPartnerService.save(partner);
		return new ResponseEntity<>(novpartner, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<PoslovniPartner> delete(@PathVariable Long id) {
		PoslovniPartner deleted = poslovniPartnerService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
	@RequestMapping(value="getPoslovniPartner", method = RequestMethod.GET)
	public ResponseEntity<List<PoslovniPartner>> getPoslovniPartner() {

		List<PoslovniPartner> partner = poslovniPartnerService.findAll();

		return new ResponseEntity<>(partner, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PoslovniPartner> getPoslovniPartner(@PathVariable Long id) {
		PoslovniPartner partner = poslovniPartnerService.findOne(id);
		if (partner == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(partner, HttpStatus.OK);
	}
		@RequestMapping(value="/all",method=RequestMethod.GET)
		public ResponseEntity<List<PoslovniPartner>> all(){
			List<PoslovniPartner> partneri=poslovniPartnerService.findAll();
			return new ResponseEntity<List<PoslovniPartner>>(partneri,HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "search")
	public ResponseEntity<List<PoslovniPartner>> searchPartner(@RequestBody PoslovniPartner pp) {
		List<PoslovniPartner> partneri=poslovniPartnerService.findAll();
		
		System.out.println(pp.getNaziv());
		if(!pp.getNaziv().equals(""))
			partneri= partneri.stream().filter(e->e.getNaziv().toUpperCase().contains(pp.getNaziv().toUpperCase())).collect(Collectors.toList());
		System.out.println(pp.getTip());
		if(pp.getTip() != null)
			partneri= partneri.stream().filter(e->e.getTip().equals(pp.getTip())).collect(Collectors.toList());
		System.out.println(pp.getPIB());
		if(pp.getPIB() !=0)
			partneri= partneri.stream().filter(e->e.getPIB()==pp.getPIB()).collect(Collectors.toList());
		System.out.println(pp.getMesto());
		if(pp.getMesto() != null){
			partneri= partneri.stream().filter(e->e.getMesto().getNaziv().equals(pp.getMesto().getNaziv())).collect(Collectors.toList());
		}
		
		return new ResponseEntity<>(partneri, HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "edit")
	public ResponseEntity<List<PoslovniPartner>> editPartnera(@RequestBody PoslovniPartner partnerEdit) {
		PoslovniPartner poslovniPartner = poslovniPartnerService.findOne(partnerEdit.getId());
		poslovniPartner.setId(partnerEdit.getId());
		poslovniPartner.setNaziv(partnerEdit.getNaziv());
		poslovniPartner.setPIB(partnerEdit.getPIB());
		poslovniPartner.setAdresa(partnerEdit.getAdresa());
		poslovniPartner.setTip(partnerEdit.getTip());
		poslovniPartner.setMesto(partnerEdit.getMesto());
		

		poslovniPartnerService.save(poslovniPartner);	
		
		return new ResponseEntity<>(poslovniPartnerService.findAll(), HttpStatus.OK);
	}
	

}