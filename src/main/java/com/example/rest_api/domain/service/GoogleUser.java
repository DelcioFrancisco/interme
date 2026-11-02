package com.example.rest_api.domain.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GoogleUser(
        String id,
        String email,
        @JsonProperty("verified_email") boolean verifiedEmail,
        String name,
        @JsonProperty("given_name") String givenName,
        @JsonProperty("family_name") String familyName,
        String picture,
        String locale
) {}
