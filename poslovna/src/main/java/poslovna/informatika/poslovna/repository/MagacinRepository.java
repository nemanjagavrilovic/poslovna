package poslovna.informatika.poslovna.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import poslovna.informatika.poslovna.model.Magacin;

@Repository
public interface MagacinRepository extends PagingAndSortingRepository<Magacin,Long>{

}
