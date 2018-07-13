package poslovna.informatika.poslovna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovna.informatika.poslovna.model.*;
import poslovna.informatika.poslovna.repository.PrometniDokumentRepository;
@Service
public class PrometniDokumentServiceImpl implements PrometniDokumentService{

	@Autowired
	private PrometniDokumentRepository prometniDokumentRepository;
	
	@Override
	public PrometniDokument save(PrometniDokument dokument) {
		// TODO Auto-generated method stub
		return prometniDokumentRepository.save(dokument);
	}
	@Override
	public List<PrometniDokument> findAll(Magacin magacin) {
		// TODO Auto-generated method stub
		return (List<PrometniDokument>) prometniDokumentRepository.findByMagacin(magacin);
	}
	@Override
	public PrometniDokument findById(Long id) {
		// TODO Auto-generated method stub
		return prometniDokumentRepository.findOne(id);
	}
	@Override
	public List<PrometniDokument> findAll() {
		// TODO Auto-generated method stub
		return (List<PrometniDokument>) prometniDokumentRepository.findAll();
	}

	@Override
	public List<PrometniDokument> findByStatusAndVrstaAndMagacinAndPoslovniPartner(StatusDokumenta status,
			VrstaPrDokumenta vrsta, Magacin magacin, PoslovniPartner partner) {
		// TODO Auto-generated method stub
		return prometniDokumentRepository.findByStatusAndVrstaAndMagacinAndPoslovniPartner(status,vrsta,magacin,partner);
		
	}

	@Override
	public List<PrometniDokument> findByStatusAndPoslovnaGodina(StatusDokumenta status, PoslovnaGodina poslovnaGodina) {
		return prometniDokumentRepository.findByStatusAndPoslovnaGodina(status, poslovnaGodina);
	}

}
