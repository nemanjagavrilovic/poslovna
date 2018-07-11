package poslovna.informatika.poslovna.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import poslovna.informatika.poslovna.model.AnalitikaMagKartice;

@Repository
public interface AnalitikaMagKarticeRepository  extends PagingAndSortingRepository<AnalitikaMagKartice,Long> {
    AnalitikaMagKartice save(AnalitikaMagKartice analitikaMagKartice);
}
