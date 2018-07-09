package poslovna.informatika.poslovna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovna.informatika.poslovna.model.Roba;
import poslovna.informatika.poslovna.repository.RobaRepository;

@Service
public class RobaServiceImpl implements RobaService{

	@Autowired
	private RobaRepository	robaRepository;
	@Override
	public Roba findById(Long id) {
		// TODO Auto-generated method stub
		return robaRepository.findOne(id);
	}

}
