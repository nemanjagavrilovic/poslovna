package poslovna.informatika.poslovna.service;

import java.util.List;

import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.PrometniDokument;

public interface PrometniDokumentService {

	public PrometniDokument save(PrometniDokument dokument);
	public List<PrometniDokument> findAll(Magacin magacin);
}
