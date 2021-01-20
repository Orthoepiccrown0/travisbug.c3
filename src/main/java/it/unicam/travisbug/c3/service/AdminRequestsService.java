package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.AdminRequests;

import java.util.List;

public interface AdminRequestsService {

    void saveAdminRequests(AdminRequests adminRequests);

    List<AdminRequests> getAll();

}
