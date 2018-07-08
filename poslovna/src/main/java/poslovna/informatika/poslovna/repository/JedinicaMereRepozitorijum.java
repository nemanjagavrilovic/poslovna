package poslovna.informatika.poslovna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poslovna.informatika.poslovna.model.JedinicaMere;

@Repository
public interface JedinicaMereRepozitorijum extends JpaRepository<JedinicaMere, Long>{
	public List<JedinicaMere> findByNazivContainingIgnoreCase(String naziv);
}
