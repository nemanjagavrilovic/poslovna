package poslovna.informatika.poslovna.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RobnaKartica {

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

	@ManyToOne
	protected PoslovnaGodina poslovnaGodina;
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
	
	
}
