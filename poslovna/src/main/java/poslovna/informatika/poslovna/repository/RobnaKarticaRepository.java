package poslovna.informatika.poslovna.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.PoslovnaGodina;
import poslovna.informatika.poslovna.model.Roba;
import poslovna.informatika.poslovna.model.RobnaKartica;

@Repository
public interface RobnaKarticaRepository extends PagingAndSortingRepository<RobnaKartica,Long>{

	List<RobnaKartica> findAll();

	//RobnaKartica save(RobnaKartica robnaKartica);

	RobnaKartica findByMagacinAndRobaAndPoslovnaGodina(Magacin magacin, Roba roba,PoslovnaGodina poslovnaGodina);

	List<RobnaKartica> findByMagacinId(Long id);

	List<RobnaKartica> findByMagacinAndPoslovnaGodina(Magacin magacin, PoslovnaGodina pg);

	RobnaKartica findByMagacinAndRoba(Magacin magacin, Roba roba);

	List<RobnaKartica> findByPoslovnaGodina(PoslovnaGodina poslovnaGodina);
}
