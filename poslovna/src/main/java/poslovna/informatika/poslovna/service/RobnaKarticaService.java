
package poslovna.informatika.poslovna.service;

import java.util.List;

import poslovna.informatika.poslovna.model.RobnaKartica;

public interface RobnaKarticaService {

	List<RobnaKartica> findAll();
	RobnaKartica save(RobnaKartica robnaKartica);
	public List<RobnaKartica> findByMagacin(Long id);
	public RobnaKartica findById(Long id);
}
