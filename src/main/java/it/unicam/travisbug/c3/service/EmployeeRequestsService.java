package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.requests.EmployeeRequests;
import it.unicam.travisbug.c3.model.shop.Shop;

import java.util.List;

public interface EmployeeRequestsService {

    void saveEmployeeRequests(EmployeeRequests employeeRequests);

    List<EmployeeRequests> getAll();

    List<EmployeeRequests> findAllByShopOrderByDateDesc(Shop shop);

}
