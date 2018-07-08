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
	
	@Column(nullable=false)
	protected float cena;
	
	@Column(nullable=false)
	protected float kolicina;
	
	@Column(nullable=false)
	protected float vrednost;
	
	@ManyToOne
	protected StavkaDokumenta stavkaDokumenta;

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

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public float getKolicina() {
		return kolicina;
	}

	public void setKolicina(float kolicina) {
		this.kolicina = kolicina;
	}

	public float getVrednost() {
		return vrednost;
	}

	public void setVrednost(float vrednost) {
		this.vrednost = vrednost;
	}

	public StavkaDokumenta getStavkaDokumenta() {
		return stavkaDokumenta;
	}

	public void setStavkaDokumenta(StavkaDokumenta stavkaDokumenta) {
		this.stavkaDokumenta = stavkaDokumenta;
	}
	
	
	
}
