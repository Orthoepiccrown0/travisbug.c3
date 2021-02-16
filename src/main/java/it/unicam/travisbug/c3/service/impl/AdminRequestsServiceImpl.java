package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.requests.AdminRequests;
import it.unicam.travisbug.c3.repository.AdminRequestsRepository;
import it.unicam.travisbug.c3.service.AdminRequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("adminRequestsService")
public class AdminRequestsServiceImpl implements AdminRequestsService {

    @Qualifier("adminRequestsRepository")
    private AdminRequestsRepository adminRequestsRepository;

    @Autowired
    public void setAdminRequestsRepository(AdminRequestsRepository adminRequestsRepository) {
        this.adminRequestsRepository = adminRequestsRepository;
    }

    @Override
    public void saveAdminRequests(AdminRequests adminRequests) {
        adminRequestsRepository.save(adminRequests);
    }

    @Override
    public List<AdminRequests> getAll() {
        return adminRequestsRepository.findAll();
    }

    public List<AdminRequests> findAllByOrderByDateDesc() {
        return adminRequestsRepository.findAllByOrderByDateDesc();
    }

    public Optional<AdminRequests> findById(String id){
        return adminRequestsRepository.findById(id);
    }

    public void deleteAdminRequest(AdminRequests adminRequests){
        adminRequestsRepository.delete(adminRequests);
    }

}
