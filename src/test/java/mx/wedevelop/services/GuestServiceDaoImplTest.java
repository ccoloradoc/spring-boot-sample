package mx.wedevelop.services;

import mx.wedevelop.model.Guest;
import mx.wedevelop.service.GuestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by colorado on 25/02/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dao")
public class GuestServiceDaoImplTest {

    private GuestService guestService;

    @Autowired
    public void setGuestService(GuestService guestService) {
        this.guestService = guestService;
    }

    @Test
    public void findAll() {
        List<Guest> guestList = guestService.findAll();
        assert guestList.size() == 4;
    }

    @Test
    public void save() {
        Guest guest = new Guest("Chumpis", 1, "/upload/chumpis.jpg");
        Guest savedGuest = guestService.saveOrUpdate(guest);

        assert guest.getName() == savedGuest.getName();
        assert guest.getAge() == savedGuest.getAge();
        assert guest.getPicture() == savedGuest.getPicture();
    }

    @Test
    public void update() {
        Guest guest = guestService.findById(1);

        assert guest.getId() == 1;
        assert guest.getName() == "Feliz";
        assert guest.getAge() == 0;
        assert guest.getPicture() == "/upload/feliz.jpg";
    }

    @Test
    public void delete() {
        guestService.delete(1);
        List<Guest> guestList = guestService.findAll();

        for(Guest guest : guestList) {
            assert guest.getId() != 1;
        }

    }
}
