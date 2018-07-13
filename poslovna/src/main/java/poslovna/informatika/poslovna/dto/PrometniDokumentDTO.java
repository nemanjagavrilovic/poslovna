package poslovna.informatika.poslovna.dto;

import java.util.Date;
import java.util.List;

public class PrometniDokumentDTO {

	private String vrsta;
	private List<Long> stavke;
	private int poslovniPartner;
	private Long magacin;
	private String status;
	private Date datumKnjizenja;
	private Date datumFormiranja;
	private int redniBr;
	private Long magacin2;
	public int getRedniBr() {
		return redniBr;
	}
	public void setRedniBr(int redniBr) {
		this.redniBr = redniBr;
	}
	public Date getDatumKnjizenja() {
		return datumKnjizenja;
	}
	public void setDatumKnjizenja(Date datumKnjizenja) {
		this.datumKnjizenja = datumKnjizenja;
	}
	public Date getDatumFormiranja() {
		return datumFormiranja;
	}
	public void setDatumFormiranja(Date datumFormiranja) {
		this.datumFormiranja = datumFormiranja;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getMagacin2() {
		return magacin2;
	}
	public void setMagacin2(Long magacin2) {
		this.magacin2 = magacin2;
	}
	
	
	
	
}
