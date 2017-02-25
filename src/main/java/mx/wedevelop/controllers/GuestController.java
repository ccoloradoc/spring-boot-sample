package mx.wedevelop.controllers;

import mx.wedevelop.model.Guest;
import mx.wedevelop.service.GuestService;
import mx.wedevelop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by colorado on 23/02/17.
 */
@Controller
public class GuestController {

    private GuestService guestService;
    private StorageService storeService;

    @Autowired
    public void setGuestService(GuestService guestService) {
        this.guestService = guestService;
    }

    @Autowired
    public void setStoreService(StorageService storeService) {
        this.storeService = storeService;
    }

    @RequestMapping("/guest")
    public String listAllguest(Model model) {
        model.addAttribute("guestList", guestService.findAll());
        return "guest/list";
    }

    @RequestMapping("/guest/{id}")
    public String findGuest(@PathVariable int id, Model model) {
        model.addAttribute("guest", guestService.findById(id));
        return "guest/show";
    }

    @RequestMapping("/guest/new")
    public String newGuest(Model model) {
        model.addAttribute("guest", new Guest());
        return "guest/new";
    }

    @RequestMapping("/guest/{id}/edit")
    public String editGuest(@PathVariable int id, Model model) {
        model.addAttribute("guest", guestService.findById(id));
        return "guest/new";
    }

    @RequestMapping(value = "/guest", method = RequestMethod.POST)
    public String saveOrUpdateGuest(
            final @RequestPart(value = "pictureFile", required = false) MultipartFile file,
            final @ModelAttribute("guest") Guest guest) throws IOException {

        //Store Image
        if(!file.isEmpty())
            guest.setPicture(storeService.store(file));

        Guest savedGuest = guestService.saveOrUpdate(guest);

        return "redirect:/guest/" + savedGuest.getId();
    }

    @RequestMapping("/guest/{id}/delete")
    public String deleteGuest(@PathVariable int id, Model model) {
        guestService.delete(id);
        return "redirect: /guest";
    }
}
