package poslovna.informatika.poslovna.dto;

import java.util.List;

public class PrometniDokumentDTO {

	private String vrsta;
	private List<Long> stavke;
	private Long poslovniPartner;
	public String getVrsta() {
		return vrsta;
	}
	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}
	public List<Long> getStavke() {
		return stavke;
	}
	public void setStavke(List<Long> stavke) {
		this.stavke = stavke;
	}
	public Long getPoslovniPartner() {
		return poslovniPartner;
	}
	public void setPoslovniPartner(Long poslovniPartner) {
		this.poslovniPartner = poslovniPartner;
	}
	
	
}

