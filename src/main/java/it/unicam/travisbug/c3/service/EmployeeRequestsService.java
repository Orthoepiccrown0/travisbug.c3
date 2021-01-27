package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.EmployeeRequests;
import it.unicam.travisbug.c3.model.Shop;

import java.util.List;

public interface EmployeeRequestsService {

    void saveEmployeeRequests(EmployeeRequests employeeRequests);

    List<EmployeeRequests> getAll();

    List<EmployeeRequests> findAllByShopOrderByDateDesc(Shop shop);

}
