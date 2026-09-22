package com.example.rest_api.domain.port.in;

import com.example.rest_api.domain.dto.AdDomain;

import java.util.List;
import java.util.Optional;

public interface ManageAdUseCase {

    AdDomain createAd(AdDomain ad);

    List<AdDomain> findAll();

    Optional<AdDomain> findById(String id);
}
