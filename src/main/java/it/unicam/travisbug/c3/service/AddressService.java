package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.order.Address;

import java.util.List;

public interface AddressService {

    void saveAddress(Address address);

    List<Address> getAll();

    Address findById(Integer id);

}
