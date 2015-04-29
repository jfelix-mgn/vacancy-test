package vacancy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vacancy.repository.DocumentRepository;

/**
 * <br>
 * <br>
 * User: felix <br>
 * Date: 28.04.15 <br>
 * Time: 16:00 <br>
 */
@Controller
@RequestMapping(method = RequestMethod.GET)
public class IndexController {

    @Autowired
    private DocumentRepository repository;

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("documents", repository.findAll());
        return "index";
    }

    @RequestMapping("reference")
    public String reference() {
        return "swagger";
    }
}
