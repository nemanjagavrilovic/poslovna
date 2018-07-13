package poslovna.informatika.poslovna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
    @RequestMapping(value = "/")
    private String welcome() {
        return "forward:/index.jsp";
    }
}
