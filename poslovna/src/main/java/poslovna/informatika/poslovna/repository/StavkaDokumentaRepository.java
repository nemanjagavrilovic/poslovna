package poslovna.informatika.poslovna.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import poslovna.informatika.poslovna.model.StavkaDokumenta;

@Repository
public interface StavkaDokumentaRepository extends PagingAndSortingRepository<StavkaDokumenta,Long>{

}
