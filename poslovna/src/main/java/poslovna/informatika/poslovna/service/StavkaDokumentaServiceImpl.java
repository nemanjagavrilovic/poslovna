package poslovna.informatika.poslovna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovna.informatika.poslovna.model.StavkaDokumenta;
import poslovna.informatika.poslovna.repository.StavkaDokumentaRepository;

@Service
public class StavkaDokumentaServiceImpl implements StavkaDokumentaService{

	@Autowired
	private StavkaDokumentaRepository stavkaDokumentaRepository;
	@Override
	public StavkaDokumenta findById(Long id) {
		// TODO Auto-generated method stub
		return stavkaDokumentaRepository.findOne(id);
	}

}
