package poslovna.informatika.poslovna.dto;

import java.util.List;

import poslovna.informatika.poslovna.model.Magacin;

public class PopisniDokumentDTO {
	private Long magacin;
	private List<Long> roba;
	private Long komisija;
	
	public PopisniDokumentDTO() {
		
	}
	public Long getMagacin() {
		return magacin;
	}
	public void setMagacin(Long magacin) {
		this.magacin = magacin;
	}
	public List<Long> getRoba() {
		return roba;
	}
	public void setRoba(List<Long> roba) {
		this.roba = roba;
	}
	public Long getKomisija() {
		return komisija;
	}
	public void setKomisija(Long komisija) {
		this.komisija = komisija;
	}
	
	
	
}
