package poslovna.informatika.poslovna.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import poslovna.informatika.poslovna.model.*;

@Repository
public interface PrometniDokumentRepository extends PagingAndSortingRepository<PrometniDokument,Long>{

	public List<PrometniDokument> findByMagacin(Magacin magacin);
	public List<PrometniDokument> findByStatusAndVrstaAndMagacinAndPoslovniPartner(StatusDokumenta status,VrstaPrDokumenta vrsta,Magacin magacin,PoslovniPartner partner);
	List<PrometniDokument> findByStatusAnAndPoslovnaGodina(StatusDokumenta status, PoslovnaGodina poslovnaGodina);
}
