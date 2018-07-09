package poslovna.informatika.poslovna.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import poslovna.informatika.poslovna.model.Roba;

@Repository
public interface RobaRepository extends PagingAndSortingRepository<Roba,Long>{

}
