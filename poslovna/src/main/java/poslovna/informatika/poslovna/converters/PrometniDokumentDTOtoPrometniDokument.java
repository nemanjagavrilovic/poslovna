package poslovna.informatika.poslovna.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import poslovna.informatika.poslovna.dto.PrometniDokumentDTO;
import poslovna.informatika.poslovna.model.PrometniDokument;
import poslovna.informatika.poslovna.model.StatusDokumenta;
import poslovna.informatika.poslovna.model.VrstaPrDokumenta;
import poslovna.informatika.poslovna.service.PoslovniPartnerService;
import poslovna.informatika.poslovna.service.StavkaDokumentaService;

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
		if(source.getVrsta().equals(VrstaPrDokumenta.MM)){
			prometniDokument.setVrsta(VrstaPrDokumenta.MM);
		}else if(source.getVrsta().equals(VrstaPrDokumenta.OT)){
			prometniDokument.setVrsta(VrstaPrDokumenta.OT);
		}else if(source.getVrsta().equals(VrstaPrDokumenta.PR)){
			prometniDokument.setVrsta(VrstaPrDokumenta.PR);
		}
		
		if(source.getStatus().equals(StatusDokumenta.F)){
			prometniDokument.setStatus(StatusDokumenta.F);
		}else if(source.getStatus().equals(StatusDokumenta.S)){
				prometniDokument.setStatus(StatusDokumenta.S);
		}else if(source.getStatus().equals(StatusDokumenta.P)){
				prometniDokument.setStatus(StatusDokumenta.P);
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
