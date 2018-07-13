package poslovna.informatika.poslovna.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import poslovna.informatika.poslovna.model.Preduzece;

@Repository
public interface PreduzeceRepository extends PagingAndSortingRepository<Preduzece,Long> {

}
