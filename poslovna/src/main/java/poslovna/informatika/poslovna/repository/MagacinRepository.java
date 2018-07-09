package poslovna.informatika.poslovna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import poslovna.informatika.poslovna.model.Magacin;

@Repository
public interface MagacinRepository extends PagingAndSortingRepository<Magacin,Long>{

	@Modifying
	@Transactional
	@Query("update Magacin m set m.naziv=?1 where m.id=?2")
	public int update(String naziv,Long id);
	
	public List<Magacin> findByNazivIgnoreCaseContaining(String naziv);
}
