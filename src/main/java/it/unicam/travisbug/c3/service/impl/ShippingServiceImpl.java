package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.Shipping;
import it.unicam.travisbug.c3.repository.ShippingRepository;
import it.unicam.travisbug.c3.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("shippingService")
public class ShippingServiceImpl implements ShippingService {

    @Qualifier("shippingRepository")
    private ShippingRepository shippingRepository;

    @Autowired
    public void setShippingRepository(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    @Override
    public void saveShipping(Shipping s) {
        shippingRepository.save(s);
    }

    @Override
    public void deleteShipping(Shipping s) {
        shippingRepository.delete(s);
    }

    @Override
    public List<Shipping> getAll() {
        return shippingRepository.findAll();
    }

    @Override
    public Shipping getShippingById(int id){
        return shippingRepository.findById(id).get();
    }

    @Override
    public void updateStatus(String status, Shipping s) {
        s = shippingRepository.findById(s.getId()).get();
        s.setShipStatus(status);
        shippingRepository.save(s);

    }

}
