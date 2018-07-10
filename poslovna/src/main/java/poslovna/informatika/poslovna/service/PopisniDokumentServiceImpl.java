package poslovna.informatika.poslovna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovna.informatika.poslovna.model.PopisniDokument;
import poslovna.informatika.poslovna.repository.PopisniDokumentRepository;

@Service
public class PopisniDokumentServiceImpl implements PopisniDokumentService{

	@Autowired
	private PopisniDokumentRepository popisniDokumentRepository;
	@Override
	public PopisniDokument save(PopisniDokument pd) {
		// TODO Auto-generated method stub
		return popisniDokumentRepository.save(pd);
	}

}
