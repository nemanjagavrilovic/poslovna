package poslovna.informatika.poslovna.service;

import java.util.List;

import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.PopisniDokument;

public interface PopisniDokumentService {
	PopisniDokument save(PopisniDokument pd);

	List<PopisniDokument> findAll();

	List<PopisniDokument> findByMagacin(Magacin magacin);

	PopisniDokument findById(Long id);
}
