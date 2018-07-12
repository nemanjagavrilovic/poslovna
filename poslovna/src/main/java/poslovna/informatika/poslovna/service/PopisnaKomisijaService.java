package poslovna.informatika.poslovna.service;

import java.util.List;

import poslovna.informatika.poslovna.model.PopisnaKomisija;

public interface PopisnaKomisijaService {

	PopisnaKomisija save(PopisnaKomisija pk);
	PopisnaKomisija findById(Long id);
	List<PopisnaKomisija> findAll();
}
