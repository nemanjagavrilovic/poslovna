package poslovna.informatika.poslovna.service;

import poslovna.informatika.poslovna.model.PoslovnaGodina;

import java.util.List;

public interface PoslovnaGodinaService {
	PoslovnaGodina findActive(boolean zakljucena);
	PoslovnaGodina findAkivna(boolean aktivna);
	PoslovnaGodina save(PoslovnaGodina poslovnaGodina);
	List<PoslovnaGodina> findAll();
	PoslovnaGodina findById(Long id);
}
