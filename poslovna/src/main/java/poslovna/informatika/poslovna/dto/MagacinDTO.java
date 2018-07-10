package poslovna.informatika.poslovna.dto;

import java.util.List;

public class MagacinDTO {

	public Long id;
	public String naziv;
	public List<Long> radnici;
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public List<Long> getRadnici() {
		return radnici;
	}
	public void setRadnici(List<Long> radnici) {
		this.radnici = radnici;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
