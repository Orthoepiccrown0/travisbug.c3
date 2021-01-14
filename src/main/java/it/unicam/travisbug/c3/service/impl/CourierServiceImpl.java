package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.Courier;
import it.unicam.travisbug.c3.repository.CourierRepository;
import it.unicam.travisbug.c3.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("courierService")
public class CourierServiceImpl implements CourierService {

    @Qualifier("courierRepository")
    private CourierRepository courierRepository;

    @Autowired
    public void setCourierRepository(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    private static CourierServiceImpl courierService;

    private CourierServiceImpl() {

    }

    public static CourierService getServiceInstance() {
        if (courierService == null)
            courierService = new CourierServiceImpl();
        return courierService;
    }

    @Override
    public void saveCourier(Courier courier) {
        courierRepository.save(courier);
    }

    @Override
    public List<Courier> getAll() {
        return null;
    }

    @Override
    public Optional<Courier> findById(String id) {
        return courierRepository.findById(id);
    }

    @Override
    public Courier findByEmail(String email) {
        return courierRepository.findByEmail(email);
    }
}
