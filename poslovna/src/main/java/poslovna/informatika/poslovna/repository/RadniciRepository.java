package poslovna.informatika.poslovna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.Radnik;

public interface RadniciRepository extends PagingAndSortingRepository<Radnik,Long>{

	@Modifying
	@Transactional
	@Query("update Radnik r set r.magacin=?1 where r.id=?2")
	public int update(Magacin magacinId,Long radnikId);
	
	public List<Radnik> findByMagacin(Magacin magacin);
}
