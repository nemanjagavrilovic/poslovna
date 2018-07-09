package poslovna.informatika.poslovna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poslovna.informatika.poslovna.model.PoslovniPartner;
import poslovna.informatika.poslovna.service.PoslovniPartnerService;

@Controller
@RequestMapping("/poslovniPartner")
public class PoslovniPartnerController {

	@Autowired
	private PoslovniPartnerService poslovniPartnerService;
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public ResponseEntity<List<PoslovniPartner>> all(){
		List<PoslovniPartner> partneri=poslovniPartnerService.findAll();
		return new ResponseEntity<List<PoslovniPartner>>(partneri,HttpStatus.OK);
	}
}
