package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.Courier;

import java.util.List;
import java.util.Optional;

public interface CourierService {
    void saveCourier(Courier courier);

    List<Courier> getAll();

    Optional<Courier> findById(String id);

    Courier findByEmail(String email);
}
