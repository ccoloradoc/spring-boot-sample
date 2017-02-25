package mx.wedevelop.controllers;

import mx.wedevelop.model.Guest;
import mx.wedevelop.service.GuestService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by colorado on 23/02/17.
 */
public class GuestControllerTest {

    @Mock
    private GuestService guestService;

    @InjectMocks
    private GuestController guestController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        //Initialize mocks
        MockitoAnnotations.initMocks(this);
        //Setup
        mockMvc = MockMvcBuilders.standaloneSetup(guestController).build();
    }

    @Test
    public void testList() throws Exception {
        List<Guest> guestList = new ArrayList<Guest>();
        guestList.add(new Guest());
        guestList.add(new Guest());
        guestList.add(new Guest());

        when(guestService.findAll()).thenReturn(guestList);

        mockMvc.perform(MockMvcRequestBuilders.get("/guest"))
            .andExpect(status().isOk())
            .andExpect(view().name("guest/list"))
            .andExpect(model().attribute("guestList", hasSize(3)));
    }

    @Test
    public void testShow() throws Exception {
        Guest guest = new Guest(1, "Firulais", "/upload/firulais.jpg");

        when(guestService.findById(guest.getId())).thenReturn(guest);

        mockMvc.perform(MockMvcRequestBuilders.get("/guest/" + guest.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("guest/guest"))
                .andExpect(model().attribute("guest", instanceOf(Guest.class)));
    }

    @Test
    public void testNew() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/guest/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("guest/new"))
                .andExpect(model().attribute("guest", instanceOf(Guest.class)));
    }

    @Test
    public void testCreate() throws Exception {
        int id = 1;
        String name = "Firulais";
        String picture = "/upload/firulais.jpg";
        int age = 1;

        Guest guest = new Guest(id, name, picture);
        guest.setAge(1);

        MockMultipartFile pictureFile = new MockMultipartFile("data", "firulais.png", "text/plain", "randomimg".getBytes());


        when(guestService.saveOrUpdate(Matchers.<Guest>any()))
                .thenReturn(guest);

        mockMvc.perform(
            MockMvcRequestBuilders.post("/guest")
            .param("id", id + "")
            .param("name", name)
            .param("age", age + "")
            .param("picture", picture))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/guest/" + id))
            .andExpect(model().attribute("guest", instanceOf(Guest.class)))
            .andExpect(model().attribute("guest", hasProperty("id", is(id))))
            .andExpect(model().attribute("guest", hasProperty("name", is(name))))
            .andExpect(model().attribute("guest", hasProperty("age", is(age))))
            .andExpect(model().attribute("guest", hasProperty("picture", is(picture))));

        ArgumentCaptor<Guest> captor = ArgumentCaptor.forClass(Guest.class);
        verify(guestService).saveOrUpdate(captor.capture());

        assertEquals(id, captor.getValue().getId());
        assertEquals(name, captor.getValue().getName());
        assertEquals(age, captor.getValue().getAge());
        assertEquals(picture, captor.getValue().getPicture());
    }
}
