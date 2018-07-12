package poslovna.informatika.poslovna.service;

import org.springframework.beans.factory.annotation.Autowired;
import poslovna.informatika.poslovna.model.AnalitikaMagKartice;
import poslovna.informatika.poslovna.model.RobnaKartica;
import poslovna.informatika.poslovna.repository.AnalitikaMagKarticeRepository;

import java.util.List;

public interface AnalitikaMagKarticeService {

    AnalitikaMagKartice save(AnalitikaMagKartice analitikaMagKartice);

    List<AnalitikaMagKartice> findByRobnaKarticaOrderByRbrAsc(RobnaKartica robnaKartica);
}
