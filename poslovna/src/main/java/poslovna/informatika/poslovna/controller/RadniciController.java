package poslovna.informatika.poslovna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import poslovna.informatika.poslovna.model.Radnik;
import poslovna.informatika.poslovna.service.RadniciService;

@Controller
@RequestMapping("/radnici")
public class RadniciController {

	@Autowired
	private RadniciService radniciService;
	
	@RequestMapping("/all")
	public ResponseEntity<List<Radnik>> radnici(){
		
		return new ResponseEntity<List<Radnik>>(radniciService.findAll(),HttpStatus.OK);
	}
	
}
