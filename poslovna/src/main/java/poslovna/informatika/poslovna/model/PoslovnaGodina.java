package poslovna.informatika.poslovna.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PoslovnaGodina {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Column(nullable=false)
	protected Date godina;
	
	@Column
	protected boolean zakljucena;

	@OneToMany
	@JsonIgnore
	protected List<RobnaKartica> kartice;
	
	@OneToMany
	@JsonIgnore
	protected List<PopisniDokument> popisniDokumenti;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getGodina() {
		return godina;
	}

	public void setGodina(Date godina) {
		this.godina = godina;
	}

	public boolean isZakljucena() {
		return zakljucena;
	}

	public void setZakljucena(boolean zakljucena) {
		this.zakljucena = zakljucena;
	}
	

	
	
}
