package poslovna.informatika.poslovna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import poslovna.informatika.poslovna.model.Mesto;
import poslovna.informatika.poslovna.model.PoslovniPartner;
import poslovna.informatika.poslovna.repository.PoslovniPartnerRepository;

@Transactional
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
	public PoslovniPartner findByPib(int pib) {
		// TODO Auto-generated method stub
		return poslovniPartnerRepository.findByPib(pib);
	}
	

	@Override
	public PoslovniPartner save(PoslovniPartner poslovniPartner) {
		// TODO Auto-generated method stub
		return poslovniPartnerRepository.save(poslovniPartner);
	}

	@Override
	public List<PoslovniPartner> save(List<PoslovniPartner> poslovniPartner) {
		// TODO Auto-generated method stub
		return poslovniPartnerRepository.save(poslovniPartner);
	}

	@Override
	public PoslovniPartner delete(Long id) {
		// TODO Auto-generated method stub
		PoslovniPartner poslovniPartner = poslovniPartnerRepository.findOne(id);
		if(poslovniPartner == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant roba");
		}
		poslovniPartnerRepository.delete(poslovniPartner);
		return poslovniPartner;
	}
	@Override
	public PoslovniPartner findOne(Long id) {
		// TODO Auto-generated method stub
		return poslovniPartnerRepository.findOne(id);
	}
/*	@Override
	public List<PoslovniPartner> findByPib(int pib) {
		// TODO Auto-generated method stub
		return poslovniPartnerRepository.findByPib(pib);
	}
*/

}
