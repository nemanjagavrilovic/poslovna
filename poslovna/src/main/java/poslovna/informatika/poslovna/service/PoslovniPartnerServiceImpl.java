package poslovna.informatika.poslovna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovna.informatika.poslovna.model.PoslovniPartner;
import poslovna.informatika.poslovna.repository.PoslovniPartnerRepository;

@Service
public class PoslovniPartnerServiceImpl implements PoslovniPartnerService{

	@Autowired
	private PoslovniPartnerRepository poslovniPartnerRepository;
	
	@Override
	public List<PoslovniPartner> findAll() {
		// TODO Auto-generated method stub
		return (List<PoslovniPartner>) poslovniPartnerRepository.findAll();
	}
	@Override
	public PoslovniPartner findByPib(int id) {
		// TODO Auto-generated method stub
		return poslovniPartnerRepository.findByPib(id);
	}

}
