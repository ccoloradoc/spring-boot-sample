package mx.wedevelop.bootstrap;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import mx.wedevelop.model.Guest;
import mx.wedevelop.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by colorado on 24/02/17.
 */
@Component
@Profile("dao")
public class SpringJPA implements ApplicationListener<ContextRefreshedEvent> {

    private GuestService guestService;

    @Autowired
    public void setGuestService(GuestService guestService) {
        this.guestService = guestService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        populateDatabase();
    }

    private void populateDatabase() {
        guestService.saveOrUpdate(new Guest(1, "Feliz", "/upload/feliz.jpg"));
        guestService.saveOrUpdate(new Guest(2, "Ojon", "/upload/ojon.jpg"));
        guestService.saveOrUpdate(new Guest(3, "Pinto", "/upload/pinto.jpg"));
        guestService.saveOrUpdate(new Guest(4, "Smarty","/upload/smarty.jpg"));
    }
}
