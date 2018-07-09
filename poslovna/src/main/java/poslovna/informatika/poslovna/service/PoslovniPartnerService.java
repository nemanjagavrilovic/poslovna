package poslovna.informatika.poslovna.service;

import java.util.List;

import poslovna.informatika.poslovna.model.PoslovniPartner;

public interface PoslovniPartnerService {

	public PoslovniPartner findByPib(int id);
	public List<PoslovniPartner> findAll();
}
