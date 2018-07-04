package poslovna.informatika.poslovna.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class Roba {

	@Id
	protected Long id;
	
	@Column(nullable=false)
	@Size(max=50)
	protected String naziv;
	
	@ManyToOne
	protected GrupaRoba grupa;

	@ManyToOne
	protected JedinicaMere jedinicaMere;
	
	@OneToMany
	protected List<StavkaPopisa> stavkaPopisa;
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

	public GrupaRoba getGrupa() {
		return grupa;
	}

	public void setGrupa(GrupaRoba grupa) {
		this.grupa = grupa;
	}
}
