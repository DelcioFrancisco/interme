package com.example.rest_api.adapter.out;

import com.example.rest_api.adapter.out.entity.Opportunity;
import com.example.rest_api.adapter.out.repository.OportunityRepository;
import com.example.rest_api.domain.dto.OpportunityDomain;
import com.example.rest_api.domain.port.out.OportunityRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaOpportunityRepository implements OportunityRepositoryPort {

    @Autowired
    private OportunityRepository repository;


    @Override
    public OpportunityDomain registerOportunity(OpportunityDomain opportunity) {
        Opportunity opportunity1 = mapToEntity(opportunity);
        repository.save(opportunity1);
        return  opportunity;
    }

    @Override
    public List<OpportunityDomain> findAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OpportunityDomain> findById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO);
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
