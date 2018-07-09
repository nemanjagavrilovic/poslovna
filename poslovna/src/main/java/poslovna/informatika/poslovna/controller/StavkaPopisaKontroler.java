package poslovna.informatika.poslovna.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import poslovna.informatika.poslovna.converters.StavkaPopisaDTOtoStavkaPopisa;
import poslovna.informatika.poslovna.dto.StavkaPopisaDTO;
import poslovna.informatika.poslovna.model.StavkaPopisa;
import poslovna.informatika.poslovna.service.StavkaPopisaService;

@RestController
@RequestMapping(value="/stavkaPopisa")
public class StavkaPopisaKontroler {
	@Autowired
	private StavkaPopisaDTOtoStavkaPopisa stavkaDTOtoStavka;
	@Autowired
	private StavkaPopisaService stavkaPopisaService;
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<List<StavkaPopisa>> addGrupaRoba(@RequestBody StavkaPopisaDTO stavkaDto) {

		List<StavkaPopisa> retStavke = new ArrayList<StavkaPopisa>();
		List<StavkaPopisa> stavke = stavkaDTOtoStavka.convert(stavkaDto);
		
		for(StavkaPopisa sp : stavke) {
			retStavke.add(stavkaPopisaService.save(sp));
		}
		
		return new ResponseEntity<>(retStavke, HttpStatus.OK);
	}

	
}
