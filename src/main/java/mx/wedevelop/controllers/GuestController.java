package mx.wedevelop.controllers;

import mx.wedevelop.model.Guest;
import mx.wedevelop.service.GuestService;
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
    public static final String UPLOADED_FOLDER =
            System.getProperty("user.dir") + "/src/main/resources/static/upload/";

    private GuestService guestService;

    @Autowired
    public void setGuestService(GuestService guestService) {
        this.guestService = guestService;
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

    @RequestMapping(value = "/guest", method = RequestMethod.POST)
    public String saveOrUpdateGuest(
            final @RequestPart(value = "pictureFile", required = false) MultipartFile file,
            final @ModelAttribute("guest") Guest guest) throws IOException {

        if(!file.isEmpty())
            guest.setPicture(saveFile(file));

        Guest savedGuest = guestService.saveOrUpdate(guest);

        return "redirect:/guest/" + savedGuest.getId();
    }

    private String saveFile(MultipartFile file) {
        String filePath = "";
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            filePath = "/upload/" + path.getFileName();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
