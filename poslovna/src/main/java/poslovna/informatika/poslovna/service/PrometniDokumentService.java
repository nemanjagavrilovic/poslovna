package poslovna.informatika.poslovna.service;

import java.util.List;

import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.PoslovniPartner;
import poslovna.informatika.poslovna.model.PrometniDokument;
import poslovna.informatika.poslovna.model.StatusDokumenta;
import poslovna.informatika.poslovna.model.VrstaPrDokumenta;

public interface PrometniDokumentService {

	public PrometniDokument save(PrometniDokument dokument);
	public List<PrometniDokument> findAll(Magacin magacin);
	public PrometniDokument findById(Long id);
	public List<PrometniDokument> findAll();
	public List<PrometniDokument> findByStatusAndVrstaAndMagacinAndPoslovniPartner(StatusDokumenta status,VrstaPrDokumenta vrsta,Magacin magacin,PoslovniPartner partner);
}