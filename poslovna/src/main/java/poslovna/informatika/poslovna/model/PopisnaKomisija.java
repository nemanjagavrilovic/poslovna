package poslovna.informatika.poslovna.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class PopisnaKomisija {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;
	
	@OneToMany
	protected List<Radnik> komisija;
	
	@OneToOne
	protected PopisniDokument dokument;
}
