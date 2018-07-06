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
public class JedinicaMere {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Column(nullable=false)
	@Size(max=30)
	protected String naziv;
	
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
	@JsonIgnore
	public List<Roba> getRobe() {
		return robe;
	}

	public void setRobe(List<Roba> robe) {
		this.robe = robe;
	}
	
	
}
