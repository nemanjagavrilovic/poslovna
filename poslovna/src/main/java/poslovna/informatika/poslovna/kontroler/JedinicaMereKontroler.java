package poslovna.informatika.poslovna.kontroler;

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
import poslovna.informatika.poslovna.servisi.JedinicaMereServis;

@RestController
@RequestMapping(value = "/api/jedinicaMere")
public class JedinicaMereKontroler {

	@Autowired
	JedinicaMereServis jedinicaMereServis;
	
	@RequestMapping(value = "getJedinicaMere", method = RequestMethod.GET)
	public ResponseEntity<List<JedinicaMere>> getJedinicaMere() {
		List<JedinicaMere> grupeRoba = jedinicaMereServis.findAll();
		return new ResponseEntity<>(grupeRoba, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<JedinicaMere> getJedinicaMere(@PathVariable Long id) {
		JedinicaMere grupaRoba = jedinicaMereServis.findOne(id);
		if (grupaRoba == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(grupaRoba, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<JedinicaMere> addGrupaRoba(@RequestBody JedinicaMere grupaRoba) {
		JedinicaMere newCountry = jedinicaMereServis.save(grupaRoba);
		return new ResponseEntity<>(newCountry, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<JedinicaMere> delete(@PathVariable Long id) {
		JedinicaMere deleted = jedinicaMereServis.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "edit")
	public ResponseEntity<List<JedinicaMere>> editContry(@RequestBody GrupaRoba grupaRoba) {
		JedinicaMere countrie = jedinicaMereServis.findOne(grupaRoba.getId());
		countrie.setNaziv(grupaRoba.getNaziv());			
		jedinicaMereServis.save(countrie);
		
		List<JedinicaMere> grupeRoba = jedinicaMereServis.findAll();
		for(int i = 0; i < grupeRoba.size(); i++){
			System.out.println(grupeRoba.get(i).getNaziv());
		}
		return new ResponseEntity<>(grupeRoba, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "search")
	public ResponseEntity<List<JedinicaMere>> searchContry(@RequestBody JedinicaMere jedinicaMere) {
		List<JedinicaMere> jediniceMere  =new ArrayList<JedinicaMere>();
		
		if(!jedinicaMere.getNaziv().equals("")){
			jediniceMere = jedinicaMereServis.findByNazivContainingIgnoreCase(jedinicaMere.getNaziv());
		}

		return new ResponseEntity<>(jediniceMere, HttpStatus.OK);
	}
}
