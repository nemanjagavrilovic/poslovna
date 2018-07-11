package poslovna.informatika.poslovna.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RobnaKartica implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Column
	protected float cena;
	

	@Column
	protected float pocetnoStanjeKol;

	@Column
	protected float pocetnoStanjeVr;

	@Column
	protected float prometUlazaKol;

	@Column
	protected float prometUlazaVr;

	@Column
	protected float prometIzlazaKol;

	@Column
	protected float prometIzlazaVr;

	@Column
	protected float ukupnaKol;

	@Column
	protected float ukupnaVr;

	
	@ManyToOne(cascade=CascadeType.ALL)
	protected Magacin magacin;
	
	@ManyToOne
	protected Roba roba;
	
	@OneToMany
	protected List<AnalitikaMagKartice> analitike;
	
	
	
	@ManyToOne
	protected PoslovnaGodina poslovnaGodina;
	
	public Magacin getMagacin() {
		return magacin;
	}

	public void setMagacin(Magacin magacin) {
		this.magacin = magacin;
	}
	
	public Roba getRoba() {
		return roba;
	}

	public void setRoba(Roba roba) {
		this.roba = roba;
	}
	@JsonIgnore
	
	public List<AnalitikaMagKartice> getAnalitike() {
		return analitike;
	}

	public void setAnalitike(List<AnalitikaMagKartice> analitike) {
		this.analitike = analitike;
	}


	@JsonIgnore
	public PoslovnaGodina getPoslovnaGodina() {
		return poslovnaGodina;
	}

	public void setPoslovnaGodina(PoslovnaGodina poslovnaGodina) {
		this.poslovnaGodina = poslovnaGodina;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public float getPocetnoStanjeKol() {
		return pocetnoStanjeKol;
	}

	public void setPocetnoStanjeKol(float pocetnoStanjeKol) {
		this.pocetnoStanjeKol = pocetnoStanjeKol;
	}

	public float getPocetnoStanjeVr() {
		return pocetnoStanjeVr;
	}

	public void setPocetnoStanjeVr(float pocetnoStanjeVr) {
		this.pocetnoStanjeVr = pocetnoStanjeVr;
	}

	public float getPrometUlazaKol() {
		return prometUlazaKol;
	}

	public void setPrometUlazaKol(float prometUlazaKol) {
		this.prometUlazaKol = prometUlazaKol;
	}

	public float getPrometUlazaVr() {
		return prometUlazaVr;
	}

	public void setPrometUlazaVr(float prometUlazaVr) {
		this.prometUlazaVr = prometUlazaVr;
	}

	public float getPrometIzlazaKol() {
		return prometIzlazaKol;
	}

	public void setPrometIzlazaKol(float prometIzlazaKol) {
		this.prometIzlazaKol = prometIzlazaKol;
	}

	public float getPrometIzlazaVr() {
		return prometIzlazaVr;
	}

	public void setPrometIzlazaVr(float prometIzlazaVr) {
		this.prometIzlazaVr = prometIzlazaVr;
	}

	public float getUkupnaKol() {
		return ukupnaKol;
	}

	public void setUkupnaKol(float ukupnaKol) {
		this.ukupnaKol = ukupnaKol;
	}

	public float getUkupnaVr() {
		return ukupnaVr;
	}

	public void setUkupnaVr(float ukupnaVr) {
		this.ukupnaVr = ukupnaVr;
	}

	public void obradiTransfer(float kolicina, float vrednost, float prosecnaCena, VrstaPrDokumenta vrstaPrDokumenta) {
		if(vrstaPrDokumenta == null) {
			ukupnaKol = kolicina;
			ukupnaVr = cena*kolicina;
		}
		else if(vrstaPrDokumenta.toString().equals("OT")) {
			prometIzlazaKol += kolicina;
			prometIzlazaVr += kolicina*prosecnaCena;
			izracunajUkupno();
		}
		else if(vrstaPrDokumenta.toString().equals("PR")){
			prometUlazaKol += kolicina;
			prometUlazaVr += kolicina*prosecnaCena;
			izracunajUkupno();
		}
		else if(vrstaPrDokumenta.toString().equals("MM")) {
			//TODO
			izracunajUkupno();
		}
		
	}

	public float nivelacija(){
		ukupnaVr+=ukupnaKol*cena-ukupnaVr;
		return ukupnaVr-analitike.get(analitike.size()-1).getUkupnaVr();
	}
	private void izracunajUkupno() {
		ukupnaKol = pocetnoStanjeKol + prometUlazaKol - prometIzlazaKol;
		ukupnaVr = pocetnoStanjeVr + prometUlazaVr - prometIzlazaVr;
	}

	public void addAnalitika(AnalitikaMagKartice analitikaMagKartice) {
		analitike.add(analitikaMagKartice);
	}
	
}
