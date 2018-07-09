package poslovna.informatika.poslovna.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PopisnaKomisija {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;
	
	@OneToMany
	@JsonIgnore
	protected List<Radnik> komisija;
	
	@OneToOne
	@JsonIgnore
	protected PopisniDokument dokument;
	
	public PopisnaKomisija() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Radnik> getKomisija() {
		return komisija;
	}

	public void setKomisija(List<Radnik> komisija) {
		this.komisija = komisija;
	}

	public PopisniDokument getDokument() {
		return dokument;
	}

	public void setDokument(PopisniDokument dokument) {
		this.dokument = dokument;
	}
	
	
	
	
}
