package poslovna.informatika.poslovna.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poslovna.informatika.poslovna.converters.StavkaDokumentaDTOtoStavkaDokumenta;
import poslovna.informatika.poslovna.dto.StavkaDokumentaDTO;
import poslovna.informatika.poslovna.model.StavkaDokumenta;
import poslovna.informatika.poslovna.service.PrometniDokumentService;
import poslovna.informatika.poslovna.service.StavkaDokumentaService;

@Controller
@RequestMapping("/stavkaDokumenta")
public class StavkaDokumentaController {

	@Autowired
	private StavkaDokumentaDTOtoStavkaDokumenta stavkaDTOtoStavka;
	
	@Autowired
	private StavkaDokumentaService stavkaDokumentaService;
	
	@Autowired
	private PrometniDokumentService prometniDokumentService;
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<StavkaDokumenta> save(@RequestBody StavkaDokumentaDTO stavkaDTO){
		
		StavkaDokumenta stavka=stavkaDTOtoStavka.convert(stavkaDTO);
		stavka=stavkaDokumentaService.save(stavka);
		return new ResponseEntity<StavkaDokumenta>(stavka,HttpStatus.CREATED);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String find(@PathVariable("id") Long id,HttpServletRequest request){
		
		request.getSession().setAttribute("stavke", stavkaDokumentaService.findByDokument(prometniDokumentService.findById(id)));
		return "forward:/stavke.jsp";
	}
	
}

