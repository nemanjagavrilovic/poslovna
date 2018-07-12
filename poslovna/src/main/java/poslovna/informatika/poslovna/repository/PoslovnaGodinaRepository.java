package poslovna.informatika.poslovna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poslovna.informatika.poslovna.model.PoslovnaGodina;

@Repository
public interface PoslovnaGodinaRepository extends JpaRepository<PoslovnaGodina,Long> {

		PoslovnaGodina findByZakljucena(boolean zakljucena);
		PoslovnaGodina findByAktivna(boolean aktivna);
		
}
