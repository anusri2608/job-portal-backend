package com.example.jobportal.service;

import com.example.jobportal.entity.Application;
import com.example.jobportal.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
    }

    public Application createApplication(Application application) {
        return applicationRepository.save(application);
    }

    public Application updateApplication(Long id, Application application) {
        Application existingApplication = getApplicationById(id);

        existingApplication.setUser(application.getUser());
        existingApplication.setJob(application.getJob());
        existingApplication.setApplicationDate(application.getApplicationDate());
        existingApplication.setStatus(application.getStatus());

        return applicationRepository.save(existingApplication);
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }
}