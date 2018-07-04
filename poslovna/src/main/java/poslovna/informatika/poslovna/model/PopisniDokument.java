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

@Entity
public class PopisniDokument {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Column
	protected Date datum;
	
	@Column
	protected int brojPopisa;

	@OneToMany
	protected List<PoslovnaGodina> poslovneGodine;	
	
	@ManyToOne
	protected Magacin magacin;
	
	@OneToOne
	protected PopisnaKomisija komisija;
	
	public Magacin getMagacin() {
		return magacin;
	}

	public void setMagacin(Magacin magacin) {
		this.magacin = magacin;
	}

	public List<PoslovnaGodina> getPoslovneGodine() {
		return poslovneGodine;
	}

	public void setPoslovneGodine(List<PoslovnaGodina> poslovneGodine) {
		this.poslovneGodine = poslovneGodine;
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
}
