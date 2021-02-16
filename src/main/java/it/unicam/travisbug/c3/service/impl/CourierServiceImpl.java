package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.users.Courier;
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
    public void deleteCourier(Courier courier) {
        courierRepository.delete(courier);
    }

    @Override
    public Courier findByEmail(String email) {
        return courierRepository.findByEmail(email);
    }

    @Override
    public Courier findByEmailAndPass(String email, String password) {
        return courierRepository.findByEmailAndPassword(email, password);
    }

}
