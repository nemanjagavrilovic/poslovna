package poslovna.informatika.poslovna.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Roba implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;
	
	@Column(nullable=false)
	@Size(max=50)
	protected String naziv;
	
	@ManyToOne(fetch = FetchType.EAGER)
	protected GrupaRoba grupa;

	@ManyToOne(fetch = FetchType.EAGER)
	protected JedinicaMere jedinicaMere;
	
	@OneToMany
	protected List<StavkaPopisa> stavkaPopisa;
	
	@OneToMany 
	protected List<RobnaKartica> robneKartice;
	public JedinicaMere getJedinicaMere() {
		return jedinicaMere;
	}

	public void setJedinicaMere(JedinicaMere jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}
	@JsonIgnore
	public List<StavkaPopisa> getStavkaPopisa() {
		return stavkaPopisa;
	}

	public void setStavkaPopisa(List<StavkaPopisa> stavkaPopisa) {
		this.stavkaPopisa = stavkaPopisa;
	}
	@JsonIgnore
	public List<RobnaKartica> getRobneKartice() {
		return robneKartice;
	}

	public void setRobneKartice(List<RobnaKartica> robneKartice) {
		this.robneKartice = robneKartice;
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
	
	public GrupaRoba getGrupa() {
		return grupa;
	}

	public void setGrupa(GrupaRoba grupa) {
		this.grupa = grupa;
	}
	
	
}
