package poslovna.informatika.poslovna.service;

import poslovna.informatika.poslovna.model.PoslovnaGodina;

import java.util.List;

public interface PoslovnaGodinaService {
	PoslovnaGodina findActive(boolean aktivna);
	PoslovnaGodina save(PoslovnaGodina poslovnaGodina);
	List<PoslovnaGodina> findAll();
}
