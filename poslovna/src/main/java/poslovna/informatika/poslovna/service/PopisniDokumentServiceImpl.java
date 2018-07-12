package poslovna.informatika.poslovna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovna.informatika.poslovna.model.Magacin;
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
	@Override
	public List<PopisniDokument> findAll() {
		// TODO Auto-generated method stub
		return popisniDokumentRepository.findAll();
	}
	@Override
	public List<PopisniDokument> findByMagacin(Magacin magacin) {
		// TODO Auto-generated method stub
		return popisniDokumentRepository.findByMagacin(magacin);
	}
	@Override
	public PopisniDokument findById(Long id) {
		// TODO Auto-generated method stub
		return popisniDokumentRepository.findOne(id);
	}
	

}
