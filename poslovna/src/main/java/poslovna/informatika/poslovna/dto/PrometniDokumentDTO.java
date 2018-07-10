package poslovna.informatika.poslovna.dto;

import java.util.List;

public class PrometniDokumentDTO {

	private String vrsta;
	private List<Long> stavke;
	private int poslovniPartner;
	private Long magacin;
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
	public int getPoslovniPartner() {
		return poslovniPartner;
	}
	public void setPoslovniPartner(int poslovniPartner) {
		this.poslovniPartner = poslovniPartner;
	}
	public Long getMagacin() {
		return magacin;
	}
	public void setMagacin(Long magacin) {
		this.magacin = magacin;
	}
	
	
	
}
