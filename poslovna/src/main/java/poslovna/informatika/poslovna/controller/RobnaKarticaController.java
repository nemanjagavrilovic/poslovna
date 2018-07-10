package poslovna.informatika.poslovna.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.Roba;
import poslovna.informatika.poslovna.model.RobnaKartica;
import poslovna.informatika.poslovna.service.MagacinService;
import poslovna.informatika.poslovna.service.RobaServis;
import poslovna.informatika.poslovna.service.RobnaKarticaService;

@Controller
@RequestMapping("/robnaKartica")
public class RobnaKarticaController {
	
	@Autowired
	private RobnaKarticaService robnaKarticaService;

	@Autowired
	private MagacinService magacinService;

	@Autowired
	private RobaServis robaServis;

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
		robnaKarticaService.save(robnaKartica);
		return new ResponseEntity<>(robnaKartica, HttpStatus.OK);
	}
}
