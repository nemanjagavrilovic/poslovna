package poslovna.informatika.poslovna.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Mesto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@Size(max=30)
	@Column(nullable=false)
	protected String naziv;
	
	@Column(nullable=false)
	protected int ptt;
	
	@OneToMany
	@JsonIgnore
	protected List<PoslovniPartner> partneri;
	public List<PoslovniPartner> getPartneri() {
		return partneri;
	}

	public void setPartneri(List<PoslovniPartner> partneri) {
		this.partneri = partneri;
	}

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


	public int getPtt() {
		return ptt;
	}

	public void setPtt(int ptt) {
		this.ptt = ptt;
	}
	
}
