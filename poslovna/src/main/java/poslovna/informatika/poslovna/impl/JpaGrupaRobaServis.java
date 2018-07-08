package poslovna.informatika.poslovna.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import poslovna.informatika.poslovna.model.GrupaRoba;
import poslovna.informatika.poslovna.repozitorijum.GrupaRobaRepozitorijum;
import poslovna.informatika.poslovna.servisi.GrupaRobaServis;

@Transactional
@Service
public class JpaGrupaRobaServis implements GrupaRobaServis{

	@Autowired
	private GrupaRobaRepozitorijum grupaRobaRepozitorijum;
	
	@Override
	public GrupaRoba findOne(Long id) {
		// TODO Auto-generated method stub
		return grupaRobaRepozitorijum.findOne(id);
	}

	@Override
	public List<GrupaRoba> findAll() {
		// TODO Auto-generated method stub
		return grupaRobaRepozitorijum.findAll();
	}

	@Override
	public GrupaRoba save(GrupaRoba grupaRoba) {
		// TODO Auto-generated method stub
		return grupaRobaRepozitorijum.save(grupaRoba);
	}

	@Override
	public List<GrupaRoba> save(List<GrupaRoba> grupeRoba) {
		// TODO Auto-generated method stub
		return grupaRobaRepozitorijum.save(grupeRoba);
	}

	@Override
	public GrupaRoba delete(Long id) {
		// TODO Auto-generated method stub
		GrupaRoba roba = grupaRobaRepozitorijum.findOne(id);
		if(roba == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant roba");
		}
		grupaRobaRepozitorijum.delete(roba);
		return roba;
	}

	@Override
	public void delete(List<Long> ids) {
		// TODO Auto-generated method stub
		for(Long id : ids){
			this.delete(id);
		}
	}

	@Override
	public List<GrupaRoba> findByNazivContainingIgnoreCase(String naziv) {
		// TODO Auto-generated method stub
		return grupaRobaRepozitorijum.findByNazivContainingIgnoreCase(naziv);
	}

}
