package poslovna.informatika.poslovna.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
public class Radnik {

	@Id
	protected Long id;
	
	@Size(max=30)
	@Column(nullable=false)
	protected String ime;
	
	@Size(max=30)
	@Column(nullable=false)
	protected String prezime;

	@Size(max=40)
	@Column(nullable=false)
	protected String adresa;
	
	
	@ManyToOne
	protected Preduzece preduzece;
}
