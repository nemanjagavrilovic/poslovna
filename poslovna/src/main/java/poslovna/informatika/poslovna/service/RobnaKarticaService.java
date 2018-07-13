
package poslovna.informatika.poslovna.service;

import java.util.List;

import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.PoslovnaGodina;
import poslovna.informatika.poslovna.model.Roba;
import poslovna.informatika.poslovna.model.RobnaKartica;

public interface RobnaKarticaService {

	List<RobnaKartica> findAll();
	RobnaKartica save(RobnaKartica robnaKartica);
	public List<RobnaKartica> findByMagacin(Long id);
	public RobnaKartica findByMagacinAndRobaAndPoslovnaGodina(Magacin magacin, Roba roba,PoslovnaGodina poslovnaGodina);
	public RobnaKartica findById(Long id);
	List<RobnaKartica> findByMagacinAndPoslovnaGodina(Magacin magacin, PoslovnaGodina pg);
	RobnaKartica findByMagacinAndRoba(Magacin magacin, Roba roba);

	List<RobnaKartica> findByPoslovnaGodina(PoslovnaGodina poslovnaGodina);
}
