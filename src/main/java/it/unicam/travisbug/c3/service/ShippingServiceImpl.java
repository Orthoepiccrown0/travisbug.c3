package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.Shipping;
import it.unicam.travisbug.c3.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("shippingService")
public class ShippingServiceImpl implements ShippingService {

    @Qualifier("shippingRepository")
    @Autowired
    private ShippingRepository shippingRepository;

    @Override
    public void saveShipping(Shipping s) {
        shippingRepository.save(s);
    }

    @Override
    public List<Shipping> getAll() {
        return shippingRepository.findAll();
    }
}
