package poslovna.informatika.poslovna.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import poslovna.informatika.poslovna.model.Roba;
import poslovna.informatika.poslovna.repozitorijum.RobaRepozitorijum;
import poslovna.informatika.poslovna.servisi.RobaServis;

@Transactional
@Service
public class JpaRobaServis implements RobaServis{

	@Autowired
	private RobaRepozitorijum robaRepozitorijum;
	
	@Override
	public Roba findOne(Long id) {
		// TODO Auto-generated method stub
		return robaRepozitorijum.findOne(id);
	}

	@Override
	public List<Roba> findAll() {
		// TODO Auto-generated method stub
		return robaRepozitorijum.findAll();
	}

	@Override
	public Roba save(Roba roba) {
		// TODO Auto-generated method stub
		return robaRepozitorijum.save(roba);
	}

	@Override
	public List<Roba> save(List<Roba> robe) {
		// TODO Auto-generated method stub
		return robaRepozitorijum.save(robe);
	}

	@Override
	public Roba delete(Long id) {
		// TODO Auto-generated method stub
		Roba roba = robaRepozitorijum.findOne(id);
		if(roba == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant roba");
		}
		robaRepozitorijum.delete(roba);
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
	public List<Roba> findByNazivContainingIgnoreCase(String naziv) {
		// TODO Auto-generated method stub
		return robaRepozitorijum.findByNazivContainingIgnoreCase(naziv);
	}

}
