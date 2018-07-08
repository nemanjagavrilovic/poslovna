package poslovna.informatika.poslovna.repozitorijum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poslovna.informatika.poslovna.model.Roba;

@Repository
public interface RobaRepozitorijum extends JpaRepository<Roba, Long>{
	public List<Roba> findByNazivContainingIgnoreCase(String naziv);
}
