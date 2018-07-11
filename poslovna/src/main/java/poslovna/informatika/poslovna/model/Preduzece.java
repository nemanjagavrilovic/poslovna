package poslovna.informatika.poslovna.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class Preduzece {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	@Size(max=50)
	@Column(nullable=false)
	protected String naziv;
	
	@Column(nullable=false)
	protected int PIB;

	@Size(max=40)
	@Column(nullable=false)
	protected String adresa;

	@ManyToOne
	protected Mesto mesto;
	
	@OneToMany
	protected List<GrupaRoba> grupaRoba;
	
	@OneToMany
	protected List<Radnik> radnici;
	
	@OneToMany
	protected List<Magacin> magacini;
	
/*	@OneToMany
	protected List<PoslovniPartner> partneri;
	*/
	public Mesto getMesto() {
		return mesto;
	}

	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}

	public List<GrupaRoba> getGrupaRoba() {
		return grupaRoba;
	}

	public void setGrupaRoba(List<GrupaRoba> grupaRoba) {
		this.grupaRoba = grupaRoba;
	}

	public List<Radnik> getRadnici() {
		return radnici;
	}

	public void setRadnici(List<Radnik> radnici) {
		this.radnici = radnici;
	}

	public List<Magacin> getMagacini() {
		return magacini;
	}

	public void setMagacini(List<Magacin> magacini) {
		this.magacini = magacini;
	}

/*	public List<PoslovniPartner> getPartneri() {
		return partneri;
	}

	public void setPartneri(List<PoslovniPartner> partneri) {
		this.partneri = partneri;
	}
*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getPIB() {
		return PIB;
	}

	public void setPIB(int pIB) {
		PIB = pIB;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
	
}
