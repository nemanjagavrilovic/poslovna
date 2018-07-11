package poslovna.informatika.poslovna.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.PoslovniPartner;
import poslovna.informatika.poslovna.model.PrometniDokument;
import poslovna.informatika.poslovna.model.StatusDokumenta;
import poslovna.informatika.poslovna.model.VrstaPrDokumenta;

@Repository
public interface PrometniDokumentRepository extends PagingAndSortingRepository<PrometniDokument,Long>{

	public List<PrometniDokument> findByMagacin(Magacin magacin);
	public List<PrometniDokument> findByStatusAndVrstaAndMagacinAndPoslovniPartner(StatusDokumenta status,VrstaPrDokumenta vrsta,Magacin magacin,PoslovniPartner partner);
}
