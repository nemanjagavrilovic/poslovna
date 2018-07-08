package poslovna.informatika.poslovna.servisi;

import java.util.List;

import poslovna.informatika.poslovna.model.*;

public interface RobaServis {

	Roba findOne(Long id);
	
	List<Roba> findAll();
	
	Roba save(Roba roba);
	
	List<Roba> save(List<Roba> robe);
	
	Roba delete(Long id);
	
	void delete(List<Long> ids);
	
	List<Roba> findByNazivContainingIgnoreCase(String naziv);
}
