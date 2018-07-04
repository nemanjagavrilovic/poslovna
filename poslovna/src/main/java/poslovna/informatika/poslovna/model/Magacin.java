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
	protected List<RobnaKartica> kartice;
	
	@ManyToOne
	protected Radnik radnik;
	
	@OneToMany
	protected List<PrometniDokument> prometniDokumenti;
	
	@OneToMany
	protected List<RobnaKartica> robneKartice;	
}
