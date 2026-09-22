package com.example.rest_api.domain.service;

import com.example.rest_api.domain.dto.AdDomain;
import com.example.rest_api.domain.port.in.ManageAdUseCase;
import com.example.rest_api.domain.port.out.AdRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdService implements ManageAdUseCase {

    private final AdRepositoryPort adRepositoryPort;

    public AdService(AdRepositoryPort adRepositoryPort) {
        this.adRepositoryPort = adRepositoryPort;
    }

    @Override
    public AdDomain createAd(AdDomain ad) {
        return adRepositoryPort.createAd(ad);
    }

    @Override
    public List<AdDomain> findAll() {
        return adRepositoryPort.findAll();
    }

    @Override
    public Optional<AdDomain> findById(String id) {
        return adRepositoryPort.findById(id);
    }
}
