package poslovna.informatika.poslovna.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import poslovna.informatika.poslovna.model.PoslovniPartner;

@Repository
public interface PoslovniPartnerRepository extends PagingAndSortingRepository<PoslovniPartner,Long> {

}
