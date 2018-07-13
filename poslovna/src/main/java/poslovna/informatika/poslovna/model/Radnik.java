package poslovna.informatika.poslovna.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Radnik implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Size(max=30)
	@Column(nullable=false)
	protected String ime;
	
	@Size(max=30)
	@Column(nullable=false)
	protected String prezime;

	@Size(max=40)
	@Column(nullable=false)
	protected String adresa;
	
	
	/*@ManyToOne
	@JsonIgnore
	protected Preduzece preduzece;
*/	
	@ManyToOne
	protected Magacin magacin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
/*	@JsonIgnore
	public Preduzece getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}
*/
	@JsonIgnore
	public Magacin getMagacin() {
		return magacin;
	}
	
	public void setMagacin(Magacin magacin) {
		this.magacin = magacin;
	}
	
}
