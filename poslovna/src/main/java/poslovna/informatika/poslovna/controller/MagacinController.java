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

import poslovna.informatika.poslovna.converters.MagacinDTOtoMagacin;
import poslovna.informatika.poslovna.dto.MagacinDTO;
import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.Radnik;
import poslovna.informatika.poslovna.model.Roba;
import poslovna.informatika.poslovna.model.RobnaKartica;
import poslovna.informatika.poslovna.service.MagacinService;
import poslovna.informatika.poslovna.service.RadniciService;

@RestController
@RequestMapping("/magacin")
public class MagacinController {

	@Autowired
	private MagacinService magacinService;
	
	@Autowired
	private RadniciService radniciService;
	@Autowired
	private MagacinDTOtoMagacin magacinDTOtoMagacin;
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<Magacin> save(@RequestBody MagacinDTO magacinDTO){
	
		Magacin magacin=magacinDTOtoMagacin.convert(magacinDTO);
		magacin=magacinService.save(magacin);
		for(Radnik radnik:magacin.getRadnici()){
			radniciService.update(magacin.getId(), radnik.getId());
			
		}
		return new ResponseEntity<Magacin>(magacin,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getRoba/{id}",method=RequestMethod.GET)
	public ResponseEntity<List<Roba>> getRoba(@PathVariable("id") Long id){
	
		List<Roba> roba = new ArrayList<Roba>();
		Magacin magacin = magacinService.findById(id);
		
		for(RobnaKartica rk : magacin.getRobneKartice()) {
			roba.add(rk.getRoba());
		}
		
		return new ResponseEntity<List<Roba>>(roba,HttpStatus.OK);
	}
}
