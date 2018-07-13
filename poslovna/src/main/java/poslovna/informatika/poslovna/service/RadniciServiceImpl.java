package poslovna.informatika.poslovna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.Radnik;
import poslovna.informatika.poslovna.repository.RadniciRepository;

@Transactional
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
	public int update(Magacin magacinId,Long radnikId) {
		// TODO Auto-generated method stub
		return radniciRepository.update(magacinId, radnikId);
	}
	@Override
	public List<Radnik> findByMagacin(Magacin magacin) {
		// TODO Auto-generated method stub
		return radniciRepository.findByMagacin(magacin);
	}
	
	
	
	@Override
	public Radnik delete(Long id) {
		// TODO Auto-generated method stub
		Radnik radnik = radniciRepository.findOne(id);
		if(radnik == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant roba");
		}
		radniciRepository.delete(radnik);
		return radnik;
	}
	@Override
	public void delete(List<Long> ids) {
		// TODO Auto-generated method stub
		for(Long id : ids){
			this.delete(id);
		}
		
	}
	@Override
	public List<Radnik> save(List<Radnik> radnik) {
		return radniciRepository.save(radnik);
	}
	@Override
	public Radnik save(Radnik radnik) {
		// TODO Auto-generated method stub
		return radniciRepository.save(radnik);
	}

}
