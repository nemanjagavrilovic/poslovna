package poslovna.informatika.poslovna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovna.informatika.poslovna.model.Preduzece;
import poslovna.informatika.poslovna.repository.PreduzeceRepository;

@Service
public class PreduzeceServiceImpl  implements PreduzeceService{

	@Autowired
	private PreduzeceRepository preduzeceRepository;
	@Override
	public Preduzece findById(Long id) {
		// TODO Auto-generated method stub
		return preduzeceRepository.findOne(id);
	}

}
