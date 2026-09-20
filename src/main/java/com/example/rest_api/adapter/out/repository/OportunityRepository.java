package com.example.rest_api.adapter.out.repository;

import com.example.rest_api.adapter.out.entity.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OportunityRepository extends JpaRepository <Opportunity, Long> {

}
