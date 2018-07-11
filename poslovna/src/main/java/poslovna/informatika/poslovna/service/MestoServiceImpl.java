package poslovna.informatika.poslovna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import poslovna.informatika.poslovna.model.Mesto;
import poslovna.informatika.poslovna.repository.MestoRepository;

@Transactional
@Service
public class MestoServiceImpl implements MestoService{
	@Autowired
	private MestoRepository mestoRepository;
	
	@Override
	public Mesto findOne(Long id) {
		// TODO Auto-generated method stub
		return mestoRepository.findOne(id);
	}

	@Override
	public List<Mesto> findAll() {
		// TODO Auto-generated method stub
		return mestoRepository.findAll();
	}

	@Override
	public Mesto save(Mesto mesto) {
		// TODO Auto-generated method stub
		return mestoRepository.save(mesto);
	}

	@Override
	public List<Mesto> save(List<Mesto> mesto) {
		// TODO Auto-generated method stub
		return mestoRepository.save(mesto);
	}

	@Override
	public Mesto delete(Long id) {
		// TODO Auto-generated method stub
		Mesto mesto = mestoRepository.findOne(id);
		if(mesto == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant roba");
		}
		mestoRepository.delete(mesto);
		return mesto;
	}

	@Override
	public void delete(List<Long> ids) {
		// TODO Auto-generated method stub
		for(Long id : ids){
			this.delete(id);
		}
	}

	@Override
	public List<Mesto> findByNazivContainingIgnoreCase(String naziv) {
		// TODO Auto-generated method stub
		return mestoRepository.findByNazivContainingIgnoreCase(naziv);
	}

	@Override
	public List<Mesto> findByPtt(int ptt) {
		// TODO Auto-generated method stub
		return mestoRepository.findByPtt(ptt);
	}

	@Override
	public List<Mesto> findByNazivAndPtt(String naziv, int ptt) {
		// TODO Auto-generated method stub
		return mestoRepository.findByNazivAndPtt(naziv,ptt);
	}
}
