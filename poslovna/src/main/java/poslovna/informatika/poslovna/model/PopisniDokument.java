package poslovna.informatika.poslovna.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PopisniDokument {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Column
	protected Date datum;
	
	@Column
	protected int brojPopisa;

	@ManyToOne
	@JsonIgnore
	protected PoslovnaGodina poslovnaGodina;	
	
	@ManyToOne
	@JsonIgnore
	protected Magacin magacin;
	
	@OneToOne
	protected PopisnaKomisija komisija;
	
	@OneToMany
	protected List<StavkaPopisa> stavke;
	
	public Magacin getMagacin() {
		return magacin;
	}

	public void setMagacin(Magacin magacin) {
		this.magacin = magacin;
	}

	public PoslovnaGodina getPoslovnaGodine() {
		return poslovnaGodina;
	}

	public void setPoslovnaGodina(PoslovnaGodina poslovneGodine) {
		this.poslovnaGodina = poslovneGodine;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getBrojPopisa() {
		return brojPopisa;
	}

	public void setBrojPopisa(int brojPopisa) {
		this.brojPopisa = brojPopisa;
	}

	public PopisnaKomisija getKomisija() {
		return komisija;
	}

	public void setKomisija(PopisnaKomisija komisija) {
		this.komisija = komisija;
	}

	public List<StavkaPopisa> getStavke() {
		return stavke;
	}

	public void setStavke(List<StavkaPopisa> stavke) {
		this.stavke = stavke;
	}
	
}
