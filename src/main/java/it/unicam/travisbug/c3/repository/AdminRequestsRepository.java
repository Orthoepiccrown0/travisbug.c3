package it.unicam.travisbug.c3.repository;

import it.unicam.travisbug.c3.model.AdminRequests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRequestsRepository extends JpaRepository<AdminRequests, String> {

    List<AdminRequests> findAllByOrderByDateDesc();

}
