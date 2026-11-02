package com.example.rest_api.domain.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OAuthService {

    public GoogleTokenResponse exchangeCodeForTokens(String code) {
        return WebClient.create()
                .post()
                .uri("https://oauth2.googleapis.com/token")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(BodyInserters.fromFormData("code", code)
                        .with("redirect_uri", "YOUR_REDIRECT_URI")
                        .with("client_id", "18872988524-mpahk09584btd5irks0clgi3vh7e245k.apps.googleusercontent.com")
                        .with("client_secret", "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")
                        .with("grant_type", "authorization_code"))
                .retrieve()
                .bodyToMono(GoogleTokenResponse.class)
                .block();
    }

    public GoogleUser getUserInfo(String accessToken) {
        return WebClient.create()
                .get()
                .uri("https://www.googleapis.com/oauth2/v2/userinfo")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(GoogleUser.class)
                .block();
    }
}