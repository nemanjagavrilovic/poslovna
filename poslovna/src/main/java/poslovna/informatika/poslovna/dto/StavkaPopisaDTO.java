package poslovna.informatika.poslovna.dto;

import java.util.List;

public class StavkaPopisaDTO {

	protected Long magacin;
	protected List<Long> stavke;
	protected List<Float> vrednosti;
	
	public StavkaPopisaDTO(){
		
	}

	public List<Long> getStavke() {
		return stavke;
	}

	public void setStavke(List<Long> stavke) {
		this.stavke = stavke;
	}

	public List<Float> getVrednosti() {
		return vrednosti;
	}

	public void setVrednosti(List<Float> vrednosti) {
		this.vrednosti = vrednosti;
	}

	public Long getMagacin() {
		return magacin;
	}

	public void setMagacin(Long magacin) {
		this.magacin = magacin;
	}
	
	
	
}
