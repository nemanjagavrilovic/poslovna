package poslovna.informatika.poslovna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.RobnaKartica;
import poslovna.informatika.poslovna.repository.RobnaKarticaRepository;

@Service
public class RobnaKarticaServiceImpl implements RobnaKarticaService {

	@Autowired
	private RobnaKarticaRepository robnaKarticaRepository;
	@Override
	public List<RobnaKartica> findByMagacin(Long id) {
		// TODO Auto-generated method stub
		return robnaKarticaRepository.findByMagacinId(id);
	}

}
