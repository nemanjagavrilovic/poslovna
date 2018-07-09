package poslovna.informatika.poslovna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import poslovna.informatika.poslovna.converters.KomisijaDTOtoPopisnaKomisija;
import poslovna.informatika.poslovna.dto.KomisijaDTO;
import poslovna.informatika.poslovna.model.PopisnaKomisija;
import poslovna.informatika.poslovna.service.PopisnaKomisijaService;

@RestController
@RequestMapping(value = "/komisija")
public class PopisnaKomisijaKontroler {
	
	@Autowired
	private KomisijaDTOtoPopisnaKomisija komisijaDTOtopopisnaKomisija;
	@Autowired
	PopisnaKomisijaService popisnaKomisijaService;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<PopisnaKomisija> addGrupaRoba(@RequestBody KomisijaDTO komisijaDto) {

		PopisnaKomisija komisija = komisijaDTOtopopisnaKomisija.convert(komisijaDto);
		System.out.println("id:"+ komisija.getId());
		komisija = popisnaKomisijaService.save(komisija);
		return new ResponseEntity<>(komisija, HttpStatus.OK);
	}

}
