package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.Shipping;

import java.util.List;

public interface ShippingService{

    void saveShipping(Shipping s);

    List<Shipping> getAll();

    void updateStatus(String status, Shipping s);

    public Shipping getShippingById(int id);
}
