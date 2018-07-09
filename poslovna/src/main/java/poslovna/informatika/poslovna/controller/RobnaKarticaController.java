package poslovna.informatika.poslovna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poslovna.informatika.poslovna.model.RobnaKartica;
import poslovna.informatika.poslovna.service.RobnaKarticaService;

@Repository
@RequestMapping("/robnaKartica")
public class RobnaKarticaController {
	
	@Autowired
	private RobnaKarticaService robnaKarticaService;

	@RequestMapping(value="/all",method=RequestMethod.GET)
	public ResponseEntity<List<RobnaKartica>> robneKartice(){
		
		List<RobnaKartica> robneKartice=robnaKarticaService.findByMagacin(1L);
		return new ResponseEntity<List<RobnaKartica>>(robneKartice,HttpStatus.OK);
	}

	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity <RobnaKartica> findById(@PathVariable("id") Long id){
		
		RobnaKartica robneKartice=robnaKarticaService.findById(id);
		return new ResponseEntity<RobnaKartica>(robneKartice,HttpStatus.OK);
	}
}

