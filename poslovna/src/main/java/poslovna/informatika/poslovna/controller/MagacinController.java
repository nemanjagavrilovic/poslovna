package poslovna.informatika.poslovna.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysql.jdbc.Connection;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import poslovna.informatika.poslovna.converters.MagacinDTOtoMagacin;
import poslovna.informatika.poslovna.dto.MagacinDTO;
import poslovna.informatika.poslovna.model.Magacin;
import poslovna.informatika.poslovna.model.Radnik;
import poslovna.informatika.poslovna.model.Roba;
import poslovna.informatika.poslovna.model.RobnaKartica;
import poslovna.informatika.poslovna.service.MagacinService;
import poslovna.informatika.poslovna.service.PreduzeceService;
import poslovna.informatika.poslovna.service.RadniciService;

@Repository
@RequestMapping("/magacin")
public class MagacinController {

	@Autowired
	private MagacinService magacinService;
	
	@Autowired
	private RadniciService radniciService;
	@Autowired
	private MagacinDTOtoMagacin magacinDTOtoMagacin;
	
	@Autowired
	private PreduzeceService preduzeceService;
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<Magacin> save(@RequestBody MagacinDTO magacinDTO){
	
		Magacin magacin=magacinDTOtoMagacin.convert(magacinDTO);
		magacin.setPreduzece(preduzeceService.findById(1L));
		magacin=magacinService.save(magacin);
		for(Radnik radnik:magacin.getRadnici()){
			radniciService.update(magacin, radnik.getId());
			
		}
		return new ResponseEntity<Magacin>(magacin,HttpStatus.OK);
	}
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public String all(HttpServletRequest request){
		
		request.getSession().setAttribute("magacini", magacinService.findAll());
		return "forward:/magacini.jsp";
	}
	@RequestMapping("/{id}")
	public ResponseEntity<Magacin> findById(@PathVariable("id") Long id){
		return new ResponseEntity<Magacin>(magacinService.findOne(id),HttpStatus.OK);
	}
	@RequestMapping("/update")
	public ResponseEntity<Magacin> update(@RequestBody MagacinDTO magacinDTO){
		Magacin magacin=magacinDTOtoMagacin.convert(magacinDTO);
		Magacin newMagacin=magacinService.findOne(magacin.getId());
		newMagacin.setNaziv(magacin.getNaziv());
		for(Radnik radnik:newMagacin.getRadnici()){
			radniciService.update(null, radnik.getId());
			
		}
		newMagacin.setRadnici(magacin.getRadnici());
		
		for(Radnik radnik:newMagacin.getRadnici()){
			radniciService.update(newMagacin, radnik.getId());
			
		}
		
		magacinService.save(newMagacin);
		return new ResponseEntity<Magacin>(newMagacin,HttpStatus.OK);
	}

	@RequestMapping(value="/search/{naziv}",method=RequestMethod.GET)
	public ResponseEntity<List<Magacin>> search(@PathVariable("naziv") String naziv){
		List<Magacin> magacini=magacinService.findByNaziv(naziv);
		return new ResponseEntity<List<Magacin>>(magacini,HttpStatus.OK);
	}
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id){
		
		magacinService.delete(magacinService.findOne(id));
	}
	
	@RequestMapping(value="/allM",method=RequestMethod.GET)
	public ResponseEntity<List<Magacin>> allM(HttpServletRequest request){
		
		List<Magacin> magacini= magacinService.findAll();
		return new ResponseEntity<List<Magacin>>(magacini,HttpStatus.OK);
	}
	@RequestMapping(value="/getRoba/{id}",method=RequestMethod.GET)
	public ResponseEntity<List<Roba>> getRoba(@PathVariable("id") Long id){
	
		List<Roba> roba = new ArrayList<Roba>();
		Magacin magacin = magacinService.findById(id);
		
		for(RobnaKartica rk : magacin.getRobneKartice()) {
			if(rk.getPoslovnaGodina().isAktivna())
				roba.add(rk.getRoba());
		}
		
		return new ResponseEntity<List<Roba>>(roba,HttpStatus.OK);
	}
	@RequestMapping(value="/izvestaj/{id}",method=RequestMethod.POST)
	public ResponseEntity<String> izvestaj(@PathVariable("id") Long id){
		try {
			Connection conn;
			conn =
				       (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/poslovna?useSSL=false&" +
				                                   "user=root&password=malizvornik95");
			HashMap map = new HashMap();
			map.put("idMagacina", id);
            JasperReport jasReport = (JasperReport) JRLoader.loadObjectFromFile("C:/Users/Nemanja/git/poslovna/poslovna/src/main/resources/listaLagera.jasper");
            JasperPrint jasPrint = JasperFillManager.fillReport(jasReport, map, conn);
            File pdf = File.createTempFile("output.", ".pdf");
			JasperExportManager.exportReportToPdfStream(jasPrint, new FileOutputStream(pdf));
			System.out.println("Temp file : " + pdf.getAbsolutePath());
		}catch (Exception ex) {
				ex.printStackTrace();
			}
		return new ResponseEntity<String>("ok",HttpStatus.OK);
		}
	
	
}
