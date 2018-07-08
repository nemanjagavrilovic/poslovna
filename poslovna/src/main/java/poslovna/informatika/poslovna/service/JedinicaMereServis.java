package poslovna.informatika.poslovna.service;

import java.util.List;

import poslovna.informatika.poslovna.model.JedinicaMere;;

public interface JedinicaMereServis {

	JedinicaMere findOne(Long id);
	
	List<JedinicaMere> findAll();
	
	JedinicaMere save(JedinicaMere jedinicaMere);
	
	List<JedinicaMere> save(List<JedinicaMere> jediniceMere);
	
	JedinicaMere delete(Long id);
	
	void delete(List<Long> ids);
	
	List<JedinicaMere> findByNazivContainingIgnoreCase(String naziv);
}
