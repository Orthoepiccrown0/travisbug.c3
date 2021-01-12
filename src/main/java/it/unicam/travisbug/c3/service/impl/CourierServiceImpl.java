package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.Courier;
import it.unicam.travisbug.c3.repository.CourierRepository;
import it.unicam.travisbug.c3.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courierService")
public class CourierServiceImpl implements CourierService {
    @Qualifier("courierRepository")
    @Autowired
    private CourierRepository courierRepository;

    @Override
    public void saveCourier(Courier courier) {
        courierRepository.save(courier);
    }

    @Override
    public List<Courier> getAll() {
        return null;
    }
}
