package poslovna.informatika.poslovna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import poslovna.informatika.poslovna.converters.PopisniDokumentDTOtoPopisniDokument;
import poslovna.informatika.poslovna.dto.PopisniDokumentDTO;
import poslovna.informatika.poslovna.model.PopisniDokument;
import poslovna.informatika.poslovna.service.PopisniDokumentService;

@RestController
@RequestMapping(value ="/popisniDokument")
public class PopisniDokumentKontroler {

	@Autowired
	private PopisniDokumentService popisniDokumentService;
	@Autowired
	private PopisniDokumentDTOtoPopisniDokument dokumentDTOtopopisniDokument;
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<PopisniDokument> save(@RequestBody PopisniDokumentDTO dokumentDTO){
		
		PopisniDokument popisniDokument = dokumentDTOtopopisniDokument.convert(dokumentDTO);
		popisniDokumentService.save(popisniDokument);
		
		return new ResponseEntity<PopisniDokument>(popisniDokument,HttpStatus.OK);
	}
}
