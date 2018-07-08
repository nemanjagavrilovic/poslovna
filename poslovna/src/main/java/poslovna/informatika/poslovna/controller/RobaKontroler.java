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

import poslovna.informatika.poslovna.model.GrupaRoba;
import poslovna.informatika.poslovna.model.JedinicaMere;
import poslovna.informatika.poslovna.model.Roba;
import poslovna.informatika.poslovna.service.GrupaRobaServis;
import poslovna.informatika.poslovna.service.JedinicaMereServis;
import poslovna.informatika.poslovna.service.RobaServis;

@RestController
@RequestMapping(value = "/api/roba")
public class RobaKontroler {

	@Autowired
	private RobaServis robaServis;
	
	@Autowired
	private GrupaRobaServis grupaRobaServis;
	
	@Autowired
	private JedinicaMereServis jedinicaMereServis;
	
	@RequestMapping(value="getRobu", method = RequestMethod.GET)
	public ResponseEntity<List<Roba>> getRobu() {

		List<Roba> roba = robaServis.findAll();

		return new ResponseEntity<>(roba, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Roba> getRoba(@PathVariable Long id) {
		Roba roba = robaServis.findOne(id);
		if (roba == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(roba, HttpStatus.OK);
	}
	

	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Roba> addCity(@RequestBody Roba roba){
			
		Roba novaRoba = robaServis.save(roba);
		return new ResponseEntity<>(novaRoba, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Roba> delete(@PathVariable Long id) {
		Roba deleted = robaServis.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "edit")
	public ResponseEntity<List<Roba>> editRoba(@RequestBody Roba robaEdit) {
		Roba roba = robaServis.findOne(robaEdit.getId());
		roba.setId(robaEdit.getId());
		roba.setNaziv(robaEdit.getNaziv());	
		GrupaRoba grupaRoba = grupaRobaServis.findOne(robaEdit.getGrupa().getId());
		JedinicaMere jedinicaMere = jedinicaMereServis.findOne(robaEdit.getJedinicaMere().getId());
		roba.setJedinicaMere(jedinicaMere);
		roba.setGrupa(grupaRoba);
		robaServis.save(roba);	
		
		return new ResponseEntity<>(robaServis.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="getRobuOdGrupeRobe", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<List<Roba>> getRobuOdGrupeRobe(@RequestBody GrupaRoba grupaRoba) {
		List<Roba> pop = robaServis.findAll();
		List<Roba> find = new ArrayList<Roba>();
		for (int i = 0; i < pop.size(); i++) {
			System.out.println(pop.get(i).getGrupa().getNaziv());
			System.out.println(grupaRoba.getNaziv());
			if (pop.get(i).getGrupa().getNaziv().equals(grupaRoba.getNaziv())) {
				find.add(pop.get(i));
			}
		}

		return new ResponseEntity<>(find, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "search")
	public ResponseEntity<List<Roba>> searchContry(@RequestBody Roba roba) {
		List<Roba> robe  =new ArrayList<Roba>();
		
		if(!roba.getNaziv().equals("")){
			robe = robaServis.findByNazivContainingIgnoreCase(roba.getNaziv());
		}

		return new ResponseEntity<>(robe, HttpStatus.OK);
	}
}
