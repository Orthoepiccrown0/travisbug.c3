package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.Courier;

import java.util.List;

public interface CourierService {
    void saveCourier(Courier courier);

    List<Courier> getAll();
}
