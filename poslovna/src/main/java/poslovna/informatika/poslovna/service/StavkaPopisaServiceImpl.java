package poslovna.informatika.poslovna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovna.informatika.poslovna.model.StavkaPopisa;
import poslovna.informatika.poslovna.repository.StavkaPopisaRepository;

@Service
public class StavkaPopisaServiceImpl implements StavkaPopisaService{

	@Autowired
	private StavkaPopisaRepository stavkaPopisaRepository;
	@Override
	public StavkaPopisa save(StavkaPopisa sp) {
		// TODO Auto-generated method stub
		return stavkaPopisaRepository.save(sp);
	}

	@Override
	public StavkaPopisa findById(Long id) {
		// TODO Auto-generated method stub
		return stavkaPopisaRepository.findOne(id);
	}

}
