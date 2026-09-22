package com.example.rest_api.adapter.out;

import com.example.rest_api.adapter.out.entity.AdEntity;
import com.example.rest_api.adapter.out.entity.AdImageEntity;
import com.example.rest_api.adapter.out.repository.AdRepository;
import com.example.rest_api.domain.dto.AdDomain;
import com.example.rest_api.domain.port.out.AdRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaAdRepository implements AdRepositoryPort {

    private final AdRepository repository;

    public JpaAdRepository(AdRepository repository) {
        this.repository = repository;
    }

    @Override
    public AdDomain createAd(AdDomain ad) {
        AdEntity entity = mapToEntity(ad);
        repository.save(entity);
        return mapToDTO(entity);
    }

    @Override
    public List<AdDomain> findAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AdDomain> findById(String id) {
        return repository.findById(id)
                .map(this::mapToDTO);
    }

    private AdEntity mapToEntity(AdDomain ad) {
        AdEntity entity = new AdEntity();
        entity.setId(ad.getId());
        entity.setTitle(ad.getTitle());
        entity.setDescription(ad.getDescription());
        entity.setPrice(ad.getPrice());

        List<AdImageEntity> images = Optional.ofNullable(ad.getImages())
                .orElseGet(ArrayList::new)
                .stream()
                .map(url -> {
                    AdImageEntity image = new AdImageEntity();
                    image.setUrl(url);
                    image.setAd(entity);
                    return image;
                })
                .collect(Collectors.toList());

        entity.setImages(images);
        return entity;
    }

    private AdDomain mapToDTO(AdEntity entity) {
        AdDomain ad = new AdDomain();
        ad.setId(entity.getId());
        ad.setTitle(entity.getTitle());
        ad.setDescription(entity.getDescription());
        ad.setPrice(entity.getPrice());

        List<String> images = Optional.ofNullable(entity.getImages())
                .orElseGet(ArrayList::new)
                .stream()
                .map(AdImageEntity::getUrl)
                .collect(Collectors.toList());

        ad.setImages(images);
        return ad;
    }
}
