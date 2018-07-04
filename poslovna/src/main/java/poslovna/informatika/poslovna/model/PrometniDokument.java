package poslovna.informatika.poslovna.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PrometniDokument {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Column
	protected VrstaPrDokumenta vrsta;
	
	@Column
	protected int redniBr;
	
	@Column
	protected Date datumForimranja;
	
	@Column
	protected Date datumKnjizenja;
	
	@Column
	protected StatusDokumenta status;

	@ManyToOne
	protected PoslovniPartner poslovniPartenr;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VrstaPrDokumenta getVrsta() {
		return vrsta;
	}

	public void setVrsta(VrstaPrDokumenta vrsta) {
		this.vrsta = vrsta;
	}

	public int getRedniBr() {
		return redniBr;
	}

	public void setRedniBr(int redniBr) {
		this.redniBr = redniBr;
	}

	public Date getDatumForimranja() {
		return datumForimranja;
	}

	public void setDatumForimranja(Date datumForimranja) {
		this.datumForimranja = datumForimranja;
	}

	public Date getDatumKnjizenja() {
		return datumKnjizenja;
	}

	public void setDatumKnjizenja(Date datumKnjizenja) {
		this.datumKnjizenja = datumKnjizenja;
	}

	public StatusDokumenta getStatus() {
		return status;
	}

	public void setStatus(StatusDokumenta status) {
		this.status = status;
	}
}

