package poslovna.informatika.poslovna.service;

import poslovna.informatika.poslovna.model.PrometniDokument;
import poslovna.informatika.poslovna.model.StavkaDokumenta;

public interface StavkaDokumentaService {

	public StavkaDokumenta findById(Long id);
	public StavkaDokumenta save(StavkaDokumenta stavka);
	public int update(PrometniDokument dokument,Long stavka);
}
