package poslovna.informatika.poslovna.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poslovna.informatika.poslovna.converters.PrometniDokumentDTOtoPrometniDokument;
import poslovna.informatika.poslovna.dto.PrometniDokumentDTO;
import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.PrometniDokument;
import poslovna.informatika.poslovna.model.StatusDokumenta;
import poslovna.informatika.poslovna.model.StavkaDokumenta;
import poslovna.informatika.poslovna.service.PrometniDokumentService;
import poslovna.informatika.poslovna.service.StavkaDokumentaService;

@Controller
@RequestMapping("/prometniDokument")
public class PrometniDokumentController {

	@Autowired
	private PrometniDokumentDTOtoPrometniDokument prometniDokumentConverter;
	
	@Autowired
	private PrometniDokumentService prometniDokumentService;
	
	@Autowired
	private StavkaDokumentaService stavkaDokumentaService;
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<PrometniDokument> save(@RequestBody PrometniDokumentDTO dokument,HttpServletRequest request){
		
		Magacin magacin=(Magacin)request.getSession().getAttribute("magacin");
		PrometniDokument prometniDokument=prometniDokumentConverter.convert(dokument);
		Calendar cal = Calendar.getInstance();
		prometniDokument.setDatumForimranja(cal.getTime());
		prometniDokument.setStatus(StatusDokumenta.F);
		prometniDokument.setMagacin(magacin);
		prometniDokument.setRedniBr(prometniDokumentService.findAll(magacin).size()+1);
		prometniDokument=prometniDokumentService.save(prometniDokument);
		for(StavkaDokumenta stavka:prometniDokument.getStavkeDokumenta()){
			stavkaDokumentaService.update(prometniDokument, stavka.getId());
		}
		return new ResponseEntity<PrometniDokument>(prometniDokument,HttpStatus.OK);
	}
}
