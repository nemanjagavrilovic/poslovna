package poslovna.informatika.poslovna.service;

import java.util.List;

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
	public List<Magacin> findAll() {
		// TODO Auto-generated method stub
		return (List<Magacin>) magacinRepository.findAll();
	}
	@Override
	public Magacin findOne(Long id) {
		// TODO Auto-generated method stub
		return magacinRepository.findOne(id);
	}
	@Override
	public int update(String naziv, Long id) {
		// TODO Auto-generated method stub
		return magacinRepository.update(naziv, id);
	}
	@Override
	public List<Magacin> findByNaziv(String naziv) {
		// TODO Auto-generated method stub
		return magacinRepository.findByNazivIgnoreCaseContaining(naziv);
	}
	@Override
	public void delete(Magacin magacin) {
		// TODO Auto-generated method stub
		magacinRepository.delete(magacin);
	}
	@Override
	public Magacin findById(Long id) {
		// TODO Auto-generated method stub
		return magacinRepository.findOne(id);
	}
}
