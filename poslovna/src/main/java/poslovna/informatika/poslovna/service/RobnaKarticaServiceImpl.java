package poslovna.informatika.poslovna.service;

import java.util.List;
import java.util.stream.Collectors;

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
	public RobnaKartica findByMagacinAndRobaAndPoslovnaGodina(Magacin magacin, Roba roba,PoslovnaGodina poslovnaGodina) {
		return robnaKarticaRepository.findByMagacinAndRobaAndPoslovnaGodina(magacin, roba,poslovnaGodina);
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

	@Override
	public RobnaKartica findByMagacinAndRoba(Magacin magacin, Roba roba) {
		return robnaKarticaRepository.findByMagacinAndRoba(magacin, roba);
	}

	@Override
	public List<RobnaKartica> findByPoslovnaGodina(PoslovnaGodina poslovnaGodina) {
		return robnaKarticaRepository.findByPoslovnaGodina(poslovnaGodina);
	}

	@Override
	public List<RobnaKartica> findByMagacinNameAndRobaName(String magacinIme, String robaIme) {
		List<RobnaKartica> robneKartice = robnaKarticaRepository.findAll();
		if(magacinIme != null && !magacinIme.isEmpty()) {
			robneKartice = robneKartice.stream()
					.filter(e -> e.getMagacin().getNaziv().toLowerCase().contains(magacinIme.toLowerCase()))
					.collect(Collectors.toList());
		}
		if(robaIme != null && !robaIme.isEmpty()) {
			robneKartice = robneKartice.stream()
					.filter(e -> e.getRoba().getNaziv().toLowerCase().contains(robaIme.toLowerCase()))
					.collect(Collectors.toList());
		}

		return robneKartice;
	}


}

