package poslovna.informatika.poslovna.service;

import java.util.List;

import poslovna.informatika.poslovna.model.PrometniDokument;
import poslovna.informatika.poslovna.model.Roba;
import poslovna.informatika.poslovna.model.StavkaDokumenta;

public interface StavkaDokumentaService {

	public StavkaDokumenta findById(Long id);
	public StavkaDokumenta save(StavkaDokumenta stavka);
	public int update(PrometniDokument dokument,Long stavka);
	public List<StavkaDokumenta> findByDokument(PrometniDokument dokument);
	public List<StavkaDokumenta> findByRobaAndKolicinaAndCenaAndVrednost(Roba roba,float kolicina,float cena,float vrednost);
}
