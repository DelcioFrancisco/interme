package com.example.rest_api.adapter.out.repository;

import com.example.rest_api.adapter.out.entity.AdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<AdEntity, String> {
}
