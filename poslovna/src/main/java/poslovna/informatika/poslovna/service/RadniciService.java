package poslovna.informatika.poslovna.service;

import java.util.List;

import poslovna.informatika.poslovna.model.Radnik;

public interface RadniciService {

	public List<Radnik> findAll();
	public Radnik findById(Long id);
	public int update(Long magacinId,Long radnikId);
}
