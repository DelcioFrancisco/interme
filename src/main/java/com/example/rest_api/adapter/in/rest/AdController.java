package com.example.rest_api.adapter.in.rest;

import com.example.rest_api.domain.dto.AdDomain;
import com.example.rest_api.domain.port.in.ManageAdUseCase;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ads")
@Slf4j
public class AdController {

    private final ManageAdUseCase adService;

    public AdController(ManageAdUseCase adService) {
        this.adService = adService;
    }

    @GetMapping
    public List<AdDomain> getAllAds() {
        log.info("Returning all ads");
        return adService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AdDomain>> getById(@PathVariable String id) {
        log.info("Returning ad with id {}", id);
        Optional<AdDomain> response = adService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdDomain createAd(@RequestBody @Valid AdDomain adDomain) {
        log.info("Creating new ad");
        return adService.createAd(adDomain);
    }
}
