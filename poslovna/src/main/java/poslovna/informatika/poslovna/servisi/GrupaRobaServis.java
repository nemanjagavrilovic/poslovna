package poslovna.informatika.poslovna.servisi;

import java.util.List;

import poslovna.informatika.poslovna.model.GrupaRoba;

public interface GrupaRobaServis {

	GrupaRoba findOne(Long id);
	
	List<GrupaRoba> findAll();
	
	GrupaRoba save(GrupaRoba grupaRoba);
	
	List<GrupaRoba> save(List<GrupaRoba> grupeRoba);
	
	GrupaRoba delete(Long id);
	
	void delete(List<Long> ids);
	
	List<GrupaRoba> findByNazivContainingIgnoreCase(String naziv);
}
