package poslovna.informatika.poslovna.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import poslovna.informatika.poslovna.dto.PopisniDokumentDTO;
import poslovna.informatika.poslovna.model.PopisniDokument;
import poslovna.informatika.poslovna.service.MagacinService;
import poslovna.informatika.poslovna.service.PopisnaKomisijaService;

@Component
public class PopisniDokumentDTOtoPopisniDokument implements Converter<PopisniDokumentDTO,PopisniDokument> {
	
	
	@Autowired
	private MagacinService magacinService;
	@Autowired PopisnaKomisijaService komisijaService;
	@Override
	public PopisniDokument convert(PopisniDokumentDTO source) {
		if(source==null)
			return null;
		
		PopisniDokument popisniDokument= new PopisniDokument() ;
			
		popisniDokument.setBrojPopisa(0);
		popisniDokument.setMagacin(magacinService.findById(source.getMagacin()));
		popisniDokument.setKomisija(komisijaService.findById(source.getKomisija()));
		
		return popisniDokument;
	}

	
}
