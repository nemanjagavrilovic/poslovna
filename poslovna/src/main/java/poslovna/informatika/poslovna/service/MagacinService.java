package poslovna.informatika.poslovna.service;

import java.util.List;

import poslovna.informatika.poslovna.model.Magacin;

public interface MagacinService {
	
	public Magacin save(Magacin magacin);
	public List<Magacin> findAll();
	public Magacin findOne(Long id);
	public int update(String naziv,Long id);
	public List<Magacin> findByNaziv(String naziv);
	public void delete(Magacin magacin);
} 
