package mx.wedevelop.service;

import mx.wedevelop.model.Guest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colorado on 23/02/17.
 */
@Service
public class GuestServiceImpl implements GuestService {
    private List<Guest> guestList;

    public GuestServiceImpl() {
        guestList = getAll();
    }

    @Override
    public List<Guest> listAll() {
        return guestList;
    }

    @Override
    public Guest getGuestById(int id) {
        return guestList.get(id);
    }

    private List<Guest> getAll() {
        List<Guest> guestList = new ArrayList<Guest>();

        guestList.add(new Guest(1, "Rex"));
        guestList.add(new Guest(2, "Sofie"));
        guestList.add(new Guest(3, "Maui"));
        guestList.add(new Guest(4, "Chucho"));
        guestList.add(new Guest(5, "Chaparro"));

        return guestList;
    }
}
