package poslovna.informatika.poslovna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import poslovna.informatika.poslovna.model.PoslovnaGodina;
import poslovna.informatika.poslovna.service.PoslovnaGodinaService;
import poslovna.informatika.poslovna.util.DateUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
@RequestMapping(value="/poslovnaGodina")
public class PoslovnaGodinaKontroler {

    @Autowired
    private PoslovnaGodinaService poslovnaGodinaService;

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity create(@RequestBody PoslovnaGodina poslovnaGodina) {
        if(poslovnaGodina.getGodina() == null) {
            return new ResponseEntity("Morate izabrati godinu.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        PoslovnaGodina staraGodina = poslovnaGodinaService.findActive(true);
        if(staraGodina != null) {
            staraGodina.setAktivna(false);
            poslovnaGodinaService.save(staraGodina);
        }
        poslovnaGodinaService.save(poslovnaGodina);
        return new ResponseEntity(poslovnaGodina, HttpStatus.OK);
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    private String index(HttpServletRequest request) {
        List<PoslovnaGodina> poslovneGodine = poslovnaGodinaService.findAll();
        System.out.println(poslovneGodine);
        request.setAttribute("godine", poslovneGodine);
        return "forward:/poslovna_godina.jsp";
    }

}
