package poslovna.informatika.poslovna.dto;

import java.util.List;

public class RobaDTO {

	private String naziv;
	private String grupa;
	private String jedinicaMere;
	private List<Long> stavkaPopisa;
	private List<Long> robneKartice;
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getGrupa() {
		return grupa;
	}
	public void setGrupa(String grupa) {
		this.grupa = grupa;
	}
	public String getJedinicaMere() {
		return jedinicaMere;
	}
	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}
	public List<Long> getStavkaPopisa() {
		return stavkaPopisa;
	}
	public void setStavkaPopisa(List<Long> stavkaPopisa) {
		this.stavkaPopisa = stavkaPopisa;
	}
	public List<Long> getRobneKartice() {
		return robneKartice;
	}
	public void setRobneKartice(List<Long> robneKartice) {
		this.robneKartice = robneKartice;
	}
	
	
}
