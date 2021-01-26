package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.Shipping;
import it.unicam.travisbug.c3.utils.ShippingStatus;

import java.util.List;

public interface ShippingService{

    void saveShipping(Shipping s);

    List<Shipping> getAll();

    List<Shipping> getAll(ShippingStatus shippingStatus);

    Shipping findById(Integer id);
}
