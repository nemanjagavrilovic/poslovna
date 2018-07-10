package poslovna.informatika.poslovna.service;

import java.util.List;

import poslovna.informatika.poslovna.model.Mesto;

public interface MestoService {
	Mesto findOne(Long id);
	
	List<Mesto> findAll();
	
	Mesto save(Mesto mesto);
	
	List<Mesto> save(List<Mesto> mesto);
	
	Mesto delete(Long id);
	
	void delete(List<Long> ids);
	
	List<Mesto> findByNazivContainingIgnoreCase(String naziv);
	List<Mesto> findByPTT(int ptt);

}
