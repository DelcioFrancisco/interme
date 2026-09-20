package com.example.rest_api.domain.service;

import com.example.rest_api.adapter.out.JpaOpportunityRepository;
import com.example.rest_api.adapter.out.entity.Opportunity;
import com.example.rest_api.domain.dto.OpportunityDomain;
import com.example.rest_api.domain.port.in.ManageOportunityUseCase;
import com.example.rest_api.domain.port.out.OportunityRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OportunityService implements OportunityRepositoryPort {
    private final JpaOpportunityRepository opportunityRepository;

    public OportunityService(JpaOpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    @Override
    public OpportunityDomain registerOportunity(OpportunityDomain opportunity) {

        return opportunityRepository.registerOportunity(opportunity);

    }

    @Override
    public List<OpportunityDomain> findAll() {
        return opportunityRepository.findAll();
    }

    @Override
    public Optional<OpportunityDomain> findById(Long id) {
        return opportunityRepository.findById(id);
    }


    private Opportunity mapToEntity(OpportunityDomain entity) {
        Opportunity opportunity = new Opportunity();
        opportunity.setId(entity.getId());
        opportunity.setNameCompany(entity.getNameCompany());
        opportunity.setDescription(entity.getDescription());
        opportunity.setValueCompany(entity.getValueCompany());
        return opportunity;
    }

    private OpportunityDomain mapToDTO(Opportunity entity) {
        OpportunityDomain opportunity = new OpportunityDomain();
        opportunity.setId(entity.getId());
        opportunity.setNameCompany(entity.getNameCompany());
        opportunity.setDescription(entity.getDescription());
        opportunity.setValueCompany(entity.getValueCompany());
        return opportunity;
    }


}
