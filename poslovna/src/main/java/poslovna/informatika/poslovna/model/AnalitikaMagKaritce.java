package poslovna.informatika.poslovna.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AnalitikaMagKaritce {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;
	
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
	
}
