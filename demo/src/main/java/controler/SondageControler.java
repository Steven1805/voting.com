package controler;

import model.Sondage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import service.SondageService;

import java.util.List;

@Controller
public class SondageControler {
    private final SondageService sondageService;

    @PutMapping("/sondage")
    public List<Sondage> putSondage(

    )
}
