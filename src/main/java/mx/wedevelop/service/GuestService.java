package mx.wedevelop.service;

import mx.wedevelop.model.Guest;

import java.util.List;

/**
 * Created by colorado on 23/02/17.
 */
public interface GuestService {
    List<Guest> findAll();
    Guest findById(int id);
    Guest saveOrUpdate(Guest guest);
    void delete(int id);
}
