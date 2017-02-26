package mx.wedevelop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by colorado on 22/02/17.
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "redirect:/guest";
    }
}
