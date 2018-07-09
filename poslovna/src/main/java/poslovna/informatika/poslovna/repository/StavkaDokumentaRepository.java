package poslovna.informatika.poslovna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import poslovna.informatika.poslovna.model.PrometniDokument;
import poslovna.informatika.poslovna.model.StavkaDokumenta;

@Repository
public interface StavkaDokumentaRepository extends PagingAndSortingRepository<StavkaDokumenta,Long>{

	@Modifying
	@Transactional
	@Query("update StavkaDokumenta s set s.dokument=?1 where s.id=?2")
	public int update(PrometniDokument dokument,Long stavka);
	
	public List<StavkaDokumenta> findByDokument(PrometniDokument dokument);
}
