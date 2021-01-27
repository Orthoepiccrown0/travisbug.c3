package it.unicam.travisbug.c3.repository;

import it.unicam.travisbug.c3.model.EmployeeRequests;
import it.unicam.travisbug.c3.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRequestsRepository extends JpaRepository<EmployeeRequests, Integer> {

    List<EmployeeRequests> findAllByShopOrderByDateDesc(Shop shop);

    List<EmployeeRequests> findAllByOrderByDateDesc();

}
