package poslovna.informatika.poslovna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovna.informatika.poslovna.model.PoslovnaGodina;
import poslovna.informatika.poslovna.repository.PoslovnaGodinaRepository;

import java.util.List;

@Service
public class PoslovnaGodinaServiceImpl implements PoslovnaGodinaService{

	@Autowired
	private PoslovnaGodinaRepository poslovnaGodinaRepository;
	@Override
	public PoslovnaGodina findActive(boolean aktivna) {
		// TODO Auto-generated method stub
		return poslovnaGodinaRepository.findByAktivna(aktivna);
	}

	@Override
	public PoslovnaGodina save(PoslovnaGodina poslovnaGodina) {
		return poslovnaGodinaRepository.save(poslovnaGodina);
	}

	@Override
	public List<PoslovnaGodina> findAll() {
		return poslovnaGodinaRepository.findAll();
	}

	@Override
	public PoslovnaGodina findAkivna(boolean aktivna) {
		// TODO Auto-generated method stub
		return poslovnaGodinaRepository.findByAktivna(aktivna);
	}

}
