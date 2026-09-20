package com.example.rest_api.adapter.in.rest;

import com.example.rest_api.domain.dto.OpportunityDomain;
import com.example.rest_api.adapter.out.repository.OportunityRepository;
import com.example.rest_api.domain.service.OportunityService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/opportunities")
@Slf4j
public class OportunityController {
    private OportunityRepository oportunities;
    private OportunityService oportunityService;

    OportunityController(OportunityRepository oportunities, OportunityService oportunityService) {
        this.oportunities = oportunities;
        this.oportunityService = oportunityService;
    }

    @GetMapping
    public List<OpportunityDomain> getAllOportunities() {
        log.info("Returning all opportunities");
        return oportunityService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<OpportunityDomain>> getById(@PathVariable Long id) {
        log.info("Returning oportunity with id " + id);
        Optional<OpportunityDomain> response = oportunityService.findById(id);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OpportunityDomain createUser(@RequestBody @Valid OpportunityDomain opportunityDomain) {
        log.info("Creating new opportunity");
        return oportunityService.registerOportunity(opportunityDomain);
    }
}
