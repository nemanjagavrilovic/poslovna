package poslovna.informatika.poslovna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovna.informatika.poslovna.model.PoslovniPartner;
import poslovna.informatika.poslovna.repository.PoslovniPartnerRepository;

@Service
public class PoslovniPartnerServiceImpl implements PoslovniPartnerService{

	@Autowired
	private PoslovniPartnerRepository poslovniPartnerRepository;
	@Override
	public PoslovniPartner findById(Long id) {
		// TODO Auto-generated method stub
		return poslovniPartnerRepository.findOne(id);
	}

}
