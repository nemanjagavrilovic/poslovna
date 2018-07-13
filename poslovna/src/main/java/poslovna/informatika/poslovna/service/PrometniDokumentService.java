package poslovna.informatika.poslovna.service;

import java.util.List;

import poslovna.informatika.poslovna.model.*;

public interface PrometniDokumentService {

	public PrometniDokument save(PrometniDokument dokument);
	public List<PrometniDokument> findAll(Magacin magacin);
	public PrometniDokument findById(Long id);
	public List<PrometniDokument> findAll();
	public List<PrometniDokument> findByStatusAndVrstaAndMagacinAndPoslovniPartner(StatusDokumenta status,VrstaPrDokumenta vrsta,Magacin magacin,PoslovniPartner partner);
	List<PrometniDokument> findByStatusAndPoslovnaGodina(StatusDokumenta status, PoslovnaGodina poslovnaGodina);
}