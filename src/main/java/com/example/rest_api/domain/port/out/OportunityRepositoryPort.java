package com.example.rest_api.domain.port.out;



import com.example.rest_api.adapter.out.entity.Opportunity;
import com.example.rest_api.domain.dto.OpportunityDomain;

import java.util.List;
import java.util.Optional;

public interface OportunityRepositoryPort {

    OpportunityDomain registerOportunity(OpportunityDomain oportunity);

    List<OpportunityDomain> findAll();

    Optional<OpportunityDomain> findById(Long id);
}
