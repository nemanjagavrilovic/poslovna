package poslovna.informatika.poslovna.service;

import java.util.List;

import poslovna.informatika.poslovna.model.StavkaPopisa;

public interface StavkaPopisaService {

	StavkaPopisa save(StavkaPopisa sp);
	StavkaPopisa findById(Long id);
	List<StavkaPopisa> findAll();
}
