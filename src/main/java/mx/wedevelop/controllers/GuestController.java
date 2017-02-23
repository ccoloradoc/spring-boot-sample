package mx.wedevelop.controllers;

import mx.wedevelop.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by colorado on 23/02/17.
 */
@Controller
public class GuestController {

    private GuestService guestService;

    @Autowired
    public void setGuestService(GuestService guestService) {
        this.guestService = guestService;
    }

    @RequestMapping("/guest")
    public String listAllguest(Model model) {
        model.addAttribute("guestList", guestService.listAll());
        return "guest/list";
    }

    @RequestMapping("/guest/{id}")
    public String findGuest(@PathVariable int id, Model model) {
        model.addAttribute("guest", guestService.getGuestById(id));
        return "guest/guest";
    }
}
