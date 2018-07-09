package poslovna.informatika.poslovna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovna.informatika.poslovna.model.PopisnaKomisija;
import poslovna.informatika.poslovna.repository.PopisnaKomisijaRepository;

@Service
public class PopisnaKomisijaServiceImpl implements PopisnaKomisijaService {

	@Autowired
	private PopisnaKomisijaRepository popisnaKomisijaRepository;
	@Override
	public PopisnaKomisija save(PopisnaKomisija pk) {
		// TODO Auto-generated method stub
		return popisnaKomisijaRepository.save(pk);
	}

	@Override
	public PopisnaKomisija findById(Long id) {
		// TODO Auto-generated method stub
		return popisnaKomisijaRepository.findOne(id);
	}

}
