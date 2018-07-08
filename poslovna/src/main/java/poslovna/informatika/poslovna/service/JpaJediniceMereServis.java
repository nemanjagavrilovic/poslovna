package poslovna.informatika.poslovna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import poslovna.informatika.poslovna.model.JedinicaMere;
import poslovna.informatika.poslovna.repository.JedinicaMereRepozitorijum;
import poslovna.informatika.poslovna.service.JedinicaMereServis;

@Transactional
@Service
public class JpaJediniceMereServis implements JedinicaMereServis{

	@Autowired
	JedinicaMereRepozitorijum jediniceMereRepozitorijum;
	
	@Override
	public JedinicaMere findOne(Long id) {
		// TODO Auto-generated method stub
		return jediniceMereRepozitorijum.findOne(id);
	}

	@Override
	public List<JedinicaMere> findAll() {
		// TODO Auto-generated method stub
		return jediniceMereRepozitorijum.findAll();
	}

	@Override
	public JedinicaMere save(JedinicaMere jedinicaMere) {
		// TODO Auto-generated method stub
		return jediniceMereRepozitorijum.save(jedinicaMere);
	}

	@Override
	public List<JedinicaMere> save(List<JedinicaMere> jediniceMere) {
		// TODO Auto-generated method stub
		return jediniceMereRepozitorijum.save(jediniceMere);
	}

	@Override
	public JedinicaMere delete(Long id) {
		// TODO Auto-generated method stub
		JedinicaMere roba = jediniceMereRepozitorijum.findOne(id);
		if(roba == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant roba");
		}
		jediniceMereRepozitorijum.delete(roba);
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
	public List<JedinicaMere> findByNazivContainingIgnoreCase(String naziv) {
		// TODO Auto-generated method stub
		return jediniceMereRepozitorijum.findByNazivContainingIgnoreCase(naziv);
	}

}
