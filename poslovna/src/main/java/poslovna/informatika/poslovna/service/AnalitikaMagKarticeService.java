package poslovna.informatika.poslovna.service;

import org.springframework.beans.factory.annotation.Autowired;
import poslovna.informatika.poslovna.model.AnalitikaMagKartice;
import poslovna.informatika.poslovna.repository.AnalitikaMagKarticeRepository;

public interface AnalitikaMagKarticeService {

    AnalitikaMagKartice save(AnalitikaMagKartice analitikaMagKartice);


}
