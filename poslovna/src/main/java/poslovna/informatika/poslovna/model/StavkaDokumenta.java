package poslovna.informatika.poslovna.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class StavkaDokumenta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Column(nullable=false)
	protected float kolicina;

	@Column(nullable=false)
	protected float cena;

	@Column(nullable=false)
	protected float vrednost;
	
	@ManyToOne
	protected Roba roba;
	
	@OneToMany
	protected List<AnalitikaMagKartice> analitike;
	
	public List<AnalitikaMagKartice> getAnalitike() {
		return analitike;
	}

	public void setAnalitike(List<AnalitikaMagKartice> analitike) {
		this.analitike = analitike;
	}

	@ManyToOne
	protected PrometniDokument dokument;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getKolicina() {
		return kolicina;
	}

	public void setKolicina(float kolicina) {
		this.kolicina = kolicina;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public float getVrednost() {
		return vrednost;
	}

	public void setVrednost(float vrednost) {
		this.vrednost = vrednost;
	}

	public Roba getRoba() {
		return roba;
	}

	public void setRoba(Roba roba) {
		this.roba = roba;
	}

	public PrometniDokument getDokument() {
		return dokument;
	}

	public void setDokument(PrometniDokument dokument) {
		this.dokument = dokument;
	}
	
}
