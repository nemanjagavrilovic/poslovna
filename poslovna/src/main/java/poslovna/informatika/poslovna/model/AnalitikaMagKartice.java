package poslovna.informatika.poslovna.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AnalitikaMagKartice {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;
	
	@Column
	protected int rbr;

	@ManyToOne
	protected RobnaKartica robnaKartica;
	
	@Column
	protected VrstaPrometa vrstaPrometa;
	
	@Column
	protected SmerPrometa smerPrometa;

	
	@ManyToOne
	protected StavkaDokumenta stavkaDokumenta;

	@Column
	protected float ukupnaKol;
	
	@Column
	protected float ukupnaVr;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRbr() {
		return rbr;
	}

	public void setRbr(int rbr) {
		this.rbr = rbr;
	}

	public RobnaKartica getRobnaKartica() {
		return robnaKartica;
	}

	public void setRobnaKartica(RobnaKartica robnaKartica) {
		this.robnaKartica = robnaKartica;
	}

	public VrstaPrometa getVrstaPrometa() {
		return vrstaPrometa;
	}

	public void setVrstaPrometa(VrstaPrometa vrstaPrometa) {
		this.vrstaPrometa = vrstaPrometa;
	}

	public SmerPrometa getSmerPrometa() {
		return smerPrometa;
	}

	public void setSmerPrometa(SmerPrometa smerPrometa) {
		this.smerPrometa = smerPrometa;
	}

	public StavkaDokumenta getStavkaDokumenta() {
		return stavkaDokumenta;
	}

	public void setStavkaDokumenta(StavkaDokumenta stavkaDokumenta) {
		this.stavkaDokumenta = stavkaDokumenta;
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
