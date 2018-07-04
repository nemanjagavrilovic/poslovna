package poslovna.informatika.poslovna.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class GrupaRoba {

	@Id
	protected Long id;
	
	@Size(max=40)
	@Column(nullable=false)
	protected String naziv;
	
	
	@ManyToOne
	protected Preduzece preduzece;
	
	@OneToMany
	protected List<Roba> robe;

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

	public Preduzece getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}

	public List<Roba> getRobe() {
		return robe;
	}

	public void setRobe(List<Roba> robe) {
		this.robe = robe;
	}
}
