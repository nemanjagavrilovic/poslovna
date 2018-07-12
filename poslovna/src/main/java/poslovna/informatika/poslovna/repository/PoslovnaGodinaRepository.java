package poslovna.informatika.poslovna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poslovna.informatika.poslovna.model.PoslovnaGodina;

import java.util.List;

@Repository
public interface PoslovnaGodinaRepository extends JpaRepository<PoslovnaGodina,Long> {

		PoslovnaGodina findByZakljucena(boolean zakljucena);
		PoslovnaGodina findByAktivna(boolean aktivna);
	

		PoslovnaGodina save(PoslovnaGodina poslovnaGodina);

		List<PoslovnaGodina> findAll();
}
