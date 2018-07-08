package poslovna.informatika.poslovna.repozitorijum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poslovna.informatika.poslovna.model.GrupaRoba;

@Repository
public interface GrupaRobaRepozitorijum extends JpaRepository<GrupaRoba, Long>{
	public List<GrupaRoba> findByNazivContainingIgnoreCase(String naziv);
}
