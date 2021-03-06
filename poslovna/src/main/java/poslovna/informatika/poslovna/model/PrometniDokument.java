
package poslovna.informatika.poslovna.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PrometniDokument implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Column
	protected VrstaPrDokumenta vrsta;
	
	@Column
	protected int redniBr;
	
	@Column
	protected Date datumFormiranja;
	
	@Column
	protected Date datumKnjizenja;
	
	@Column
	protected StatusDokumenta status;

	@ManyToOne
	protected PoslovniPartner poslovniPartner;
	
	@OneToMany(mappedBy="dokument")
	protected List<StavkaDokumenta> stavkeDokumenta;
	
	@ManyToOne
	protected Magacin magacin;
	
	@ManyToOne
	protected Magacin magacin2;
	public PoslovniPartner getPoslovniPartner() {
		return poslovniPartner;
	}
	@ManyToOne
	protected PoslovnaGodina poslovnaGodina;
	public void setPoslovniPartner(PoslovniPartner poslovniPartner) {
		this.poslovniPartner = poslovniPartner;
	}
	
	public Magacin getMagacin() {
		return magacin;
	}

	public void setMagacin(Magacin magacin) {
		this.magacin = magacin;
	}

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

	public Date getDatumFormiranja() {
		Date date = new Date(datumFormiranja.getTime());
		System.out.println(date);
		return date;
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

	public PoslovniPartner getPoslovniPartenr() {
		return poslovniPartner;
	}

	public void setPoslovniPartenr(PoslovniPartner poslovniPartenr) {
		this.poslovniPartner = poslovniPartenr;
	}
	@JsonIgnore
	public List<StavkaDokumenta> getStavkeDokumenta() {
		return stavkeDokumenta;
	}

	public void setStavkeDokumenta(List<StavkaDokumenta> stavkeDokumenta) {
		this.stavkeDokumenta = stavkeDokumenta;
	}

	public Magacin getMagacin2() {
		return magacin2;
	}

	public void setMagacin2(Magacin magacin2) {
		this.magacin2 = magacin2;
	}

	public void setDatumFormiranja(Date datumFormiranja) {
		this.datumFormiranja = datumFormiranja;
	}

	public PoslovnaGodina getPoslovnaGodina() {
		return poslovnaGodina;
	}

	public void setPoslovnaGodina(PoslovnaGodina poslovnaGodina) {
		this.poslovnaGodina = poslovnaGodina;
	}
	
}


