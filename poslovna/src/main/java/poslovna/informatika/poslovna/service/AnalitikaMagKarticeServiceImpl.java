package poslovna.informatika.poslovna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poslovna.informatika.poslovna.model.AnalitikaMagKartice;
import poslovna.informatika.poslovna.repository.AnalitikaMagKarticeRepository;

@Service
public class AnalitikaMagKarticeServiceImpl implements AnalitikaMagKarticeService {

    @Autowired
    private AnalitikaMagKarticeRepository analitikaMagKarticeRepository;

    @Override
    public AnalitikaMagKartice save(AnalitikaMagKartice analitikaMagKartice) {
        return analitikaMagKarticeRepository.save(analitikaMagKartice);
    }

}
