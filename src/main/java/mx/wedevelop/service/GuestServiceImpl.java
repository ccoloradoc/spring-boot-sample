package mx.wedevelop.service;

import mx.wedevelop.model.Guest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colorado on 23/02/17.
 */
@Service
@Profile("map")
public class GuestServiceImpl implements GuestService {
    private List<Guest> guestList;

    public GuestServiceImpl() {
        guestList = getAll();
    }

    @Override
    public List<Guest> findAll() {
        return guestList;
    }

    @Override
    public Guest findById(int id) {
        return guestList.get(id - 1);
    }

    @Override
    public Guest saveOrUpdate(Guest guest) {
        if(guest.getId() == 0) {
            guest.setId(guestList.size() + 1);
        }
        guestList.add(guest);
        return guest;
    }

    @Override
    public void delete(int id) {

    }

    private List<Guest> getAll() {
        List<Guest> guestList = new ArrayList<Guest>();

        guestList.add(new Guest(1, "Feliz", "/upload/feliz.jpg"));
        guestList.add(new Guest(2, "Ojon", "/upload/ojon.jpg"));
        guestList.add(new Guest(3, "Pinto", "/upload/pinto.jpg"));
        guestList.add(new Guest(4, "Smarty","/upload/smarty.jpg"));

        return guestList;
    }
}
