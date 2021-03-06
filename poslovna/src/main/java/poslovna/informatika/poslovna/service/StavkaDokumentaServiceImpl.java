package poslovna.informatika.poslovna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovna.informatika.poslovna.model.PrometniDokument;
import poslovna.informatika.poslovna.model.Roba;
import poslovna.informatika.poslovna.model.StavkaDokumenta;
import poslovna.informatika.poslovna.repository.StavkaDokumentaRepository;

@Service
public class StavkaDokumentaServiceImpl implements StavkaDokumentaService{

	@Autowired
	private StavkaDokumentaRepository stavkaDokumentaRepository;
	@Override
	public StavkaDokumenta findById(Long id) {
		// TODO Auto-generated method stub
		return stavkaDokumentaRepository.findOne(id);
	}
	@Override
	public StavkaDokumenta save(StavkaDokumenta stavka) {
		// TODO Auto-generated method stub
		return stavkaDokumentaRepository.save(stavka);
	}
	@Override
	public int update(PrometniDokument dokument, Long stavka) {
		// TODO Auto-generated method stub
		return stavkaDokumentaRepository.update(dokument, stavka);
	}
	@Override
	public List<StavkaDokumenta> findByDokument(PrometniDokument dokument) {
		// TODO Auto-generated method stub
		return stavkaDokumentaRepository.findByDokument(dokument);
	}
	@Override
	public List<StavkaDokumenta> findByRobaAndKolicinaAndCenaAndVrednost(Roba roba, float kolicina, float cena,
			float vrednost) {
		// TODO Auto-generated method stub
		return stavkaDokumentaRepository.findByRobaOrKolicinaLessThanOrCenaLessThanOrVrednostLessThan(roba, kolicina, cena, vrednost);
	}

}


