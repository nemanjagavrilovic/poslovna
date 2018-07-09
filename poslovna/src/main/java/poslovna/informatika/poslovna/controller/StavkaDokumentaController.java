package poslovna.informatika.poslovna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poslovna.informatika.poslovna.converters.StavkaDokumentaDTOtoStavkaDokumenta;
import poslovna.informatika.poslovna.dto.StavkaDokumentaDTO;
import poslovna.informatika.poslovna.model.StavkaDokumenta;
import poslovna.informatika.poslovna.service.StavkaDokumentaService;

@Controller
@RequestMapping("/stavkaDokumenta")
public class StavkaDokumentaController {

	@Autowired
	private StavkaDokumentaDTOtoStavkaDokumenta stavkaDTOtoStavka;
	
	@Autowired
	private StavkaDokumentaService stavkaDokumentaService;
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<StavkaDokumenta> save(@RequestBody StavkaDokumentaDTO stavkaDTO){
		
		StavkaDokumenta stavka=stavkaDTOtoStavka.convert(stavkaDTO);
		stavka=stavkaDokumentaService.save(stavka);
		return new ResponseEntity<StavkaDokumenta>(stavka,HttpStatus.CREATED);
	}
	
}
