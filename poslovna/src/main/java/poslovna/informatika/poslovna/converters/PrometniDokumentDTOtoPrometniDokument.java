package poslovna.informatika.poslovna.converters;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import poslovna.informatika.poslovna.dto.PrometniDokumentDTO;
import poslovna.informatika.poslovna.model.PrometniDokument;
import poslovna.informatika.poslovna.model.StatusDokumenta;
import poslovna.informatika.poslovna.model.StavkaDokumenta;
import poslovna.informatika.poslovna.model.VrstaPrDokumenta;
import poslovna.informatika.poslovna.service.MagacinService;
import poslovna.informatika.poslovna.service.PoslovniPartnerService;
import poslovna.informatika.poslovna.service.StavkaDokumentaService;
@Component
public class PrometniDokumentDTOtoPrometniDokument implements Converter<PrometniDokumentDTO,PrometniDokument>{

	@Autowired
	private PoslovniPartnerService poslovniPartnerSerivce;

	@Autowired
	private StavkaDokumentaService stavkaDokumentaService;
	
	@Autowired
	private MagacinService magacinService;
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
		if(source.getStatus()!=null){
			if(source.getStatus().equals("F")){
				prometniDokument.setStatus(StatusDokumenta.F);
			}else if(source.getStatus().equals("P")){
				prometniDokument.setStatus(StatusDokumenta.P);
			}else if(source.getStatus().equals("S")){
				prometniDokument.setStatus(StatusDokumenta.S);
			}
		}
		if(source.getMagacin()!=null){
			prometniDokument.setMagacin(magacinService.findOne(source.getMagacin()));
		}
	
		
		if(source.getPoslovniPartner()!=-1){
			prometniDokument.setPoslovniPartenr(poslovniPartnerSerivce.findByPib(source.getPoslovniPartner()));
		}
		if(source.getStavke()!= null && source.getStavke().size()!=0){
			for(Long id:source.getStavke()){
				prometniDokument.getStavkeDokumenta().add(stavkaDokumentaService.findById(id));
			}
		}
		if(source.getMagacin2()!=null){
			System.out.println("Magacin 2"+source.getMagacin2());
			
			prometniDokument.setMagacin2(magacinService.findOne(source.getMagacin2()));
		}
		return prometniDokument;
	}

}
