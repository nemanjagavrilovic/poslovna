package poslovna.informatika.poslovna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.PoslovnaGodina;
import poslovna.informatika.poslovna.model.Roba;
import poslovna.informatika.poslovna.model.RobnaKartica;
import poslovna.informatika.poslovna.repository.RobnaKarticaRepository;

@Service
public class RobnaKarticaServiceImpl implements RobnaKarticaService {

	@Autowired
	private RobnaKarticaRepository robnaKarticaRepository;

	@Override
	public List<RobnaKartica> findAll() {
		return robnaKarticaRepository.findAll();
	}

	
	@Override
	@Transactional
	public RobnaKartica save(RobnaKartica robnaKartica) {
		return robnaKarticaRepository.save(robnaKartica);
	}

	@Override
	public List<RobnaKartica> findByMagacin(Long id) {
		// TODO Auto-generated method stub
		return robnaKarticaRepository.findByMagacinId(id);
	}

	@Override
	public RobnaKartica findByMagacinAndRoba(Magacin magacin, Roba roba) {
		return robnaKarticaRepository.findByMagacinAndRoba(magacin, roba);
	}

	@Override
	public RobnaKartica findById(Long id) {
		// TODO Auto-generated method stub
		return robnaKarticaRepository.findOne(id);
	}

	@Override
	public List<RobnaKartica> findByMagacinAndPoslovnaGodina(Magacin magacin, PoslovnaGodina pg) {
		// TODO Auto-generated method stub
		return robnaKarticaRepository.findByMagacinAndPoslovnaGodina(magacin,pg);
	}

}

