package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.requests.EmployeeRequests;
import it.unicam.travisbug.c3.model.shop.Shop;
import it.unicam.travisbug.c3.repository.EmployeeRequestsRepository;
import it.unicam.travisbug.c3.service.EmployeeRequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeRequestsServiceImpl implements EmployeeRequestsService {

    @Qualifier("employeeRequestsRepository")
    private EmployeeRequestsRepository employeeRequestsRepository;

    @Autowired
    public void setAdminRequestsRepository(EmployeeRequestsRepository employeeRequestsRepository) {
        this.employeeRequestsRepository = employeeRequestsRepository;
    }

    @Override
    public void saveEmployeeRequests(EmployeeRequests employeeRequests) {
        employeeRequestsRepository.save(employeeRequests);
    }

    @Override
    public List<EmployeeRequests> getAll() {
        return employeeRequestsRepository.findAll();
    }

    public List<EmployeeRequests> findAllByShopOrderByDateDesc(Shop shop){
        return employeeRequestsRepository.findAllByShopOrderByDateDesc(shop);
    }

    public List<EmployeeRequests> findAllByOrderByDateDesc() {
        return employeeRequestsRepository.findAllByOrderByDateDesc();
    }

    public Optional<EmployeeRequests> findById(Integer id){
        return employeeRequestsRepository.findById(id);
    }

    public void deleteEmployeeRequest(EmployeeRequests employeeRequests){
        employeeRequestsRepository.delete(employeeRequests);
    }

}
