package poslovna.informatika.poslovna.converters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import poslovna.informatika.poslovna.dto.PopisniDokumentDTO;
import poslovna.informatika.poslovna.model.PopisniDokument;
import poslovna.informatika.poslovna.model.StavkaPopisa;
import poslovna.informatika.poslovna.service.MagacinService;
import poslovna.informatika.poslovna.service.PopisnaKomisijaService;
import poslovna.informatika.poslovna.service.PopisniDokumentService;
import poslovna.informatika.poslovna.service.StavkaPopisaService;

@Component
public class PopisniDokumentDTOtoPopisniDokument implements Converter<PopisniDokumentDTO,PopisniDokument> {
	
	
	@Autowired
	private MagacinService magacinService;
	@Autowired
	private PopisniDokumentService popisniDokumentService;
	@Autowired 
	private PopisnaKomisijaService komisijaService;
	@Autowired
	private StavkaPopisaService stavkaPopisaService;
	@Override
	public PopisniDokument convert(PopisniDokumentDTO source) {
		if(source==null)
			return null;
		
		PopisniDokument popisniDokument= new PopisniDokument() ;
			
		popisniDokument.setDatum(new Date());
		List<PopisniDokument> dokumenti = popisniDokumentService.findAll();
		popisniDokument.setBrojPopisa(dokumenti.size()+1);
		popisniDokument.setMagacin(magacinService.findById(source.getMagacin()));
		popisniDokument.setKomisija(komisijaService.findById(source.getKomisija()));
		List<StavkaPopisa> stavke = new ArrayList<StavkaPopisa>();
		
		for(Long id : source.getRoba()) {
			stavke.add(stavkaPopisaService.findById(id));
		}
		
		popisniDokument.setStavke(stavke);
		return popisniDokument;
	}

	
}
