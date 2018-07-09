package poslovna.informatika.poslovna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.repository.MagacinRepository;

@Service
public class MagacinServiceImpl implements MagacinService {

	@Autowired
	private MagacinRepository magacinRepository;
	@Override
	public Magacin save(Magacin magacin) {
		// TODO Auto-generated method stub
		return magacinRepository.save(magacin);
	}
	@Override
	public Magacin findById(Long id) {
		// TODO Auto-generated method stub
		return magacinRepository.findOne(id);
	}

}
