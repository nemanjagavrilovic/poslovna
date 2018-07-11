package poslovna.informatika.poslovna.service;

import java.util.List;

import poslovna.informatika.poslovna.model.PoslovniPartner;


public interface PoslovniPartnerService {

	public PoslovniPartner findByPib(int id);
	public List<PoslovniPartner> findAll();
	
	PoslovniPartner findOne(Long id);
	
	PoslovniPartner save(PoslovniPartner poslovniPartner);
	
	List<PoslovniPartner> save(List<PoslovniPartner> poslovniPartner);
	
	PoslovniPartner delete(Long id);
	
//	void delete(List<Long> ids);

}
