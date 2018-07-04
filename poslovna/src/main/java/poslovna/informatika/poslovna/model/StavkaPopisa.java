package poslovna.informatika.poslovna.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class StavkaPopisa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Column(nullable=false)
	protected float kolicinaPoPopisu;
	
	@Column(nullable=false)
	protected float kolicinaPoKartici;

	
	@ManyToOne
	protected Roba roba;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getKolicinaPoPopisu() {
		return kolicinaPoPopisu;
	}

	public void setKolicinaPoPopisu(float kolicinaPoPopisu) {
		this.kolicinaPoPopisu = kolicinaPoPopisu;
	}

	public float getKolicinaPoKartici() {
		return kolicinaPoKartici;
	}

	public void setKolicinaPoKartici(float kolicinaPoKartici) {
		this.kolicinaPoKartici = kolicinaPoKartici;
	}
	
	
}
