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
public class PoslovniPartner {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Column(nullable=false)
	protected TipPartnera tip;
	
	@Size(max=40)	
	@Column(nullable=false)
	protected String naziv;
	
	@Column(nullable=false)
	protected int pib;
	
	@Size(max=40)
	@Column(nullable=false)
	protected String adresa;
	
	@ManyToOne
	protected Mesto mesto;

	@OneToMany
	protected List<PrometniDokument> dokumenti;
	

	public List<PrometniDokument> getDokumenti() {
		return dokumenti;
	}

	public void setDokumenti(List<PrometniDokument> dokumenti) {
		this.dokumenti = dokumenti;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipPartnera getTip() {
		return tip;
	}

	public void setTip(TipPartnera tip) {
		this.tip = tip;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getPIB() {
		return pib;
	}

	public void setPIB(int pib) {
		this.pib = pib;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Mesto getMesto() {
		return mesto;
	}

	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}
	
	
}
