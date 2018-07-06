package poslovna.informatika.poslovna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovna.informatika.poslovna.model.Radnik;
import poslovna.informatika.poslovna.repository.RadniciRepository;

@Service
public class RadniciServiceImpl implements RadniciService{

	@Autowired
	private RadniciRepository radniciRepository;
	@Override
	public List<Radnik> findAll() {
		// TODO Auto-generated method stub
		return (List<Radnik>) radniciRepository.findAll();
	}
	@Override
	public Radnik findById(Long id) {
		// TODO Auto-generated method stub
		return radniciRepository.findOne(id);
	}
	@Override
	public int update(Long magacinId,Long radnikId) {
		// TODO Auto-generated method stub
		return radniciRepository.update(magacinId, radnikId);
	}

}
