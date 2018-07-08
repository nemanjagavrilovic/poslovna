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
import poslovna.informatika.poslovna.servisi.GrupaRobaServis;


@RestController
@RequestMapping(value = "/api/grupaRoba")
public class GrupaRobaKontroler {

	@Autowired
	private GrupaRobaServis grupaRobaServis;
	
	
	@RequestMapping(value = "getGrupaRoba", method = RequestMethod.GET)
	public ResponseEntity<List<GrupaRoba>> getCountries() {
		List<GrupaRoba> grupeRoba = grupaRobaServis.findAll();
		return new ResponseEntity<>(grupeRoba, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<GrupaRoba> getCountry(@PathVariable Long id) {
		GrupaRoba grupaRoba = grupaRobaServis.findOne(id);
		if (grupaRoba == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(grupaRoba, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<GrupaRoba> addGrupaRoba(@RequestBody GrupaRoba grupaRoba) {
		GrupaRoba newCountry = grupaRobaServis.save(grupaRoba);
		return new ResponseEntity<>(newCountry, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<GrupaRoba> delete(@PathVariable Long id) {
		GrupaRoba deleted = grupaRobaServis.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "edit")
	public ResponseEntity<List<GrupaRoba>> editContry(@RequestBody GrupaRoba grupaRoba) {
		GrupaRoba countrie = grupaRobaServis.findOne(grupaRoba.getId());
		countrie.setNaziv(grupaRoba.getNaziv());			
		grupaRobaServis.save(countrie);
		
		List<GrupaRoba> grupeRoba = grupaRobaServis.findAll();
		for(int i = 0; i < grupeRoba.size(); i++){
			System.out.println(grupeRoba.get(i).getNaziv());
		}
		return new ResponseEntity<>(grupeRoba, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "search")
	public ResponseEntity<List<GrupaRoba>> searchContry(@RequestBody GrupaRoba grupaRoba) {
		List<GrupaRoba> grupeRobe  =new ArrayList<GrupaRoba>();
		
		if(!grupaRoba.getNaziv().equals("")){
			grupeRobe = grupaRobaServis.findByNazivContainingIgnoreCase(grupaRoba.getNaziv());
		}

		return new ResponseEntity<>(grupeRobe, HttpStatus.OK);
	}
}
