package poslovna.informatika.poslovna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.PrometniDokument;
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

}
