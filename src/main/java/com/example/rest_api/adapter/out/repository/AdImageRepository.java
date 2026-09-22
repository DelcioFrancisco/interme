package com.example.rest_api.adapter.out.repository;

import com.example.rest_api.adapter.out.entity.AdImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdImageRepository extends JpaRepository<AdImageEntity, Long> {
}
