package poslovna.informatika.poslovna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poslovna.informatika.poslovna.model.Mesto;

@Repository
public interface MestoRepository  extends JpaRepository<Mesto, Long>{
	
	public List<Mesto> findByNazivContainingIgnoreCase(String naziv);

}
