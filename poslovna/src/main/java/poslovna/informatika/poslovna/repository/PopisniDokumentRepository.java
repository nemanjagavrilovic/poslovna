package poslovna.informatika.poslovna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.PopisniDokument;

public interface PopisniDokumentRepository extends JpaRepository<PopisniDokument, Long> {

	List<PopisniDokument> findByMagacin(Magacin magacin);

}
