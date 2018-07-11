package poslovna.informatika.poslovna.service;

import poslovna.informatika.poslovna.model.PoslovnaGodina;

public interface PoslovnaGodinaService {
	PoslovnaGodina findActive(boolean zakljucena);
}
