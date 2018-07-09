package poslovna.informatika.poslovna.converters;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import poslovna.informatika.poslovna.dto.PrometniDokumentDTO;
import poslovna.informatika.poslovna.model.PrometniDokument;
import poslovna.informatika.poslovna.model.StavkaDokumenta;
import poslovna.informatika.poslovna.model.VrstaPrDokumenta;
import poslovna.informatika.poslovna.service.PoslovniPartnerService;
import poslovna.informatika.poslovna.service.StavkaDokumentaService;
@Component
public class PrometniDokumentDTOtoPrometniDokument implements Converter<PrometniDokumentDTO,PrometniDokument>{

	@Autowired
	private PoslovniPartnerService poslovniPartnerSerivce;

	@Autowired
	private StavkaDokumentaService stavkaDokumentaService;
	@Override
	public PrometniDokument convert(PrometniDokumentDTO source) {
		// TODO Auto-generated method stub
		if(source==null)
			return null;
		PrometniDokument prometniDokument=new PrometniDokument();
		prometniDokument.setStavkeDokumenta(new ArrayList<StavkaDokumenta>());
		if(source.getVrsta().equals("MM")){
			prometniDokument.setVrsta(VrstaPrDokumenta.MM);
		}else if(source.getVrsta().equals("OT")){
			prometniDokument.setVrsta(VrstaPrDokumenta.OT);
		}else if(source.getVrsta().equals("PR")){
			prometniDokument.setVrsta(VrstaPrDokumenta.PR);
		}
		
	
		
		if(source.getPoslovniPartner()!=null){
			prometniDokument.setPoslovniPartenr(poslovniPartnerSerivce.findById(source.getPoslovniPartner()));
		}
		if(source.getStavke().size()!=0){
			for(Long id:source.getStavke()){
				prometniDokument.getStavkeDokumenta().add(stavkaDokumentaService.findById(id));
			}
		}
		return prometniDokument;
	}

}

