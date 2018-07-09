package poslovna.informatika.poslovna.service;

import java.util.List;


import poslovna.informatika.poslovna.model.PoslovniPartner;

public interface PoslovniPartnerService {

	public PoslovniPartner findById(Long id);
	
	PoslovniPartner findOne(Long id);
	
	List<PoslovniPartner> findAll();
	
	PoslovniPartner save(PoslovniPartner poslovniPartner);
	
	List<PoslovniPartner> save(List<PoslovniPartner> poslovniPartner);
	
	PoslovniPartner delete(Long id);
	
	void delete(List<Long> ids);
	
	List<PoslovniPartner> findByNazivContainingIgnoreCase(String naziv);
	
}
