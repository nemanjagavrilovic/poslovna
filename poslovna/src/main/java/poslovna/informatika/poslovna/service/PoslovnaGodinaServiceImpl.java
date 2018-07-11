package poslovna.informatika.poslovna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovna.informatika.poslovna.model.PoslovnaGodina;
import poslovna.informatika.poslovna.repository.PoslovnaGodinaRepository;

@Service
public class PoslovnaGodinaServiceImpl implements PoslovnaGodinaService{

	@Autowired
	private PoslovnaGodinaRepository poslovnaGodinaRepository;
	@Override
	public PoslovnaGodina findActive(boolean zakljucena) {
		// TODO Auto-generated method stub
		return poslovnaGodinaRepository.findByZakljucena(zakljucena);
	}

}
