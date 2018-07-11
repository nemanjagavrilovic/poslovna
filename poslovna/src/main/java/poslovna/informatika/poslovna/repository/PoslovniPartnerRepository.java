package poslovna.informatika.poslovna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poslovna.informatika.poslovna.model.PoslovniPartner;

@Repository
public interface PoslovniPartnerRepository extends JpaRepository<PoslovniPartner,Long> {

	public PoslovniPartner findByPib(int pib);
}
