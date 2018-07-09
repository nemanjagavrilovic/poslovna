package poslovna.informatika.poslovna.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poslovna.informatika.poslovna.converters.MagacinDTOtoMagacin;
import poslovna.informatika.poslovna.dto.MagacinDTO;
import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.Radnik;
import poslovna.informatika.poslovna.model.Roba;
import poslovna.informatika.poslovna.model.RobnaKartica;
import poslovna.informatika.poslovna.service.MagacinService;
import poslovna.informatika.poslovna.service.RadniciService;

@Repository
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
			radniciService.update(magacin, radnik.getId());
			
		}
		return new ResponseEntity<Magacin>(magacin,HttpStatus.OK);
	}
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public String all(HttpServletRequest request){
		
		request.getSession().setAttribute("magacini", magacinService.findAll());
		return "forward:/magacini.jsp";
	}
	@RequestMapping("/{id}")
	public ResponseEntity<Magacin> findById(@PathVariable("id") Long id){
		return new ResponseEntity<Magacin>(magacinService.findOne(id),HttpStatus.OK);
	}
	@RequestMapping("/update")
	public ResponseEntity<Magacin> update(@RequestBody MagacinDTO magacinDTO){
		Magacin magacin=magacinDTOtoMagacin.convert(magacinDTO);
		Magacin newMagacin=magacinService.findOne(magacin.getId());
		newMagacin.setNaziv(magacin.getNaziv());
		newMagacin.setRadnici(magacin.getRadnici());
		for(Radnik radnik:newMagacin.getRadnici()){
			radniciService.update(newMagacin, radnik.getId());
			
		}
		magacinService.save(newMagacin);
		return new ResponseEntity<Magacin>(newMagacin,HttpStatus.OK);
	}

	@RequestMapping(value="/search/{naziv}",method=RequestMethod.GET)
	public ResponseEntity<List<Magacin>> search(@PathVariable("naziv") String naziv){
		List<Magacin> magacini=magacinService.findByNaziv(naziv);
		return new ResponseEntity<List<Magacin>>(magacini,HttpStatus.OK);
	}
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id){
		
		magacinService.delete(magacinService.findOne(id));
	}
	
	@RequestMapping(value="/allM",method=RequestMethod.GET)
	public ResponseEntity<List<Magacin>> allM(HttpServletRequest request){
		
		List<Magacin> magacini= magacinService.findAll();
		return new ResponseEntity<List<Magacin>>(magacini,HttpStatus.OK);
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
