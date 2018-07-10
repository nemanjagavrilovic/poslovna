
package poslovna.informatika.poslovna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import poslovna.informatika.poslovna.model.PoslovniPartner;
import poslovna.informatika.poslovna.service.PoslovniPartnerService;



@RestController
@RequestMapping(value = "/api/poslovniPartner")
public class PoslovniPartnerController {
	
	@Autowired
	private PoslovniPartnerService poslovniPartner;
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<PoslovniPartner> addPoslovniPartner(@RequestBody PoslovniPartner partner){
			
		PoslovniPartner novpartner = poslovniPartner.save(partner);
		return new ResponseEntity<>(novpartner, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<PoslovniPartner> delete(@PathVariable Long id) {
		PoslovniPartner deleted = poslovniPartner.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
	@RequestMapping(value="getPoslovniPartner", method = RequestMethod.GET)
	public ResponseEntity<List<PoslovniPartner>> getPoslovniPartner() {

		List<PoslovniPartner> partner = poslovniPartner.findAll();

		return new ResponseEntity<>(partner, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PoslovniPartner> getPoslovniPartner(@PathVariable Long id) {
		PoslovniPartner partner = poslovniPartner.findOne(id);
		if (partner == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(partner, HttpStatus.OK);
	}
	

}