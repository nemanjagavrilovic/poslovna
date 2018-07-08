package poslovna.informatika.poslovna.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Magacin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Column(nullable=false)
	protected String naziv;
	
	@OneToMany
	protected List<PopisniDokument> dokumenti;
	
	@ManyToOne
	protected Preduzece preduzece;
	
	
	@OneToMany
	protected List<Radnik> radnici;
	
	@OneToMany
	protected List<PrometniDokument> prometniDokumenti;
	
	@OneToMany
	protected List<RobnaKartica> robneKartice;

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

	public List<PopisniDokument> getDokumenti() {
		return dokumenti;
	}

	public void setDokumenti(List<PopisniDokument> dokumenti) {
		this.dokumenti = dokumenti;
	}

	public Preduzece getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}

	public List<Radnik> getRadnici() {
		return radnici;
	}

	public void setRadnici(List<Radnik> radnici) {
		this.radnici = radnici;
	}

	public List<PrometniDokument> getPrometniDokumenti() {
		return prometniDokumenti;
	}

	public void setPrometniDokumenti(List<PrometniDokument> prometniDokumenti) {
		this.prometniDokumenti = prometniDokumenti;
	}

	public List<RobnaKartica> getRobneKartice() {
		return robneKartice;
	}

	public void setRobneKartice(List<RobnaKartica> robneKartice) {
		this.robneKartice = robneKartice;
	}	
}
