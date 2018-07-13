package poslovna.informatika.poslovna.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import poslovna.informatika.poslovna.converters.KomisijaDTOtoPopisnaKomisija;
import poslovna.informatika.poslovna.dto.KomisijaDTO;
import poslovna.informatika.poslovna.model.PopisnaKomisija;
import poslovna.informatika.poslovna.model.PopisniDokument;
import poslovna.informatika.poslovna.model.Radnik;
import poslovna.informatika.poslovna.service.PopisnaKomisijaService;
import poslovna.informatika.poslovna.service.PopisniDokumentService;

@RestController
@RequestMapping(value = "/komisija")
public class PopisnaKomisijaKontroler {

	@Autowired
	private KomisijaDTOtoPopisnaKomisija komisijaDTOtopopisnaKomisija;
	@Autowired
	PopisnaKomisijaService popisnaKomisijaService;
	@Autowired
	private PopisniDokumentService popisniDokumentService;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<PopisnaKomisija> addGrupaRoba(@RequestBody KomisijaDTO komisijaDto) {

		PopisnaKomisija komisija = komisijaDTOtopopisnaKomisija.convert(komisijaDto);
		System.out.println("id:" + komisija.getId());
		komisija = popisnaKomisijaService.save(komisija);
		return new ResponseEntity<>(komisija, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<PopisnaKomisija>> getAll() {

		List<PopisnaKomisija> stavke = popisnaKomisijaService.findAll();

		return new ResponseEntity<>(stavke, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAll/{id}", method = RequestMethod.GET)
	public ResponseEntity<PopisnaKomisija> getAll(@PathVariable("id") Long id) {
		PopisniDokument dokument = popisniDokumentService.findById(id);
		PopisnaKomisija komisija = dokument.getKomisija();

		return new ResponseEntity<>(komisija, HttpStatus.OK);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<List<PopisnaKomisija>> search(@RequestBody Radnik radnik) {

		List<PopisnaKomisija> komisija = popisnaKomisijaService.findAll();
		List<PopisnaKomisija> retKomisija = new ArrayList<PopisnaKomisija>();
		if (!radnik.getIme().equals("") && !radnik.getPrezime().equals("")) {
			for (PopisnaKomisija pk : komisija) {
				for (Radnik r : pk.getKomisija()) {
					if (r.getIme().toUpperCase().contains(radnik.getIme().toUpperCase())
							&& r.getPrezime().toUpperCase().contains(radnik.getPrezime().toUpperCase())) {
						retKomisija.add(pk);
						break;
					}
				}
			}
		}
		else if (!radnik.getIme().equals("")) {
			for (PopisnaKomisija pk : komisija) {
				for (Radnik r : pk.getKomisija()) {

					if (r.getIme().toUpperCase().contains(radnik.getIme().toUpperCase())) {
						retKomisija.add(pk);
						break;
					}
				}
			}
		}
		else if (!radnik.getPrezime().equals("")) {
			for (PopisnaKomisija pk : komisija) {
				for (Radnik r : pk.getKomisija()) {

					if (r.getPrezime().toUpperCase().contains(radnik.getPrezime().toUpperCase())) {
						retKomisija.add(pk);
						break;
					}
				}
			}

		}
		return new ResponseEntity<>(retKomisija, HttpStatus.OK);
	}

}
