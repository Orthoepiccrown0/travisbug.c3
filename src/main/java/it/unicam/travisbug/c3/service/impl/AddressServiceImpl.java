package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.Address;
import it.unicam.travisbug.c3.repository.AddressRepository;
import it.unicam.travisbug.c3.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class AddressServiceImpl implements AddressService {

    @Qualifier("addressRepository")
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void saveAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }
}
