package com.example.rest_api.adapter.in.rest;

import com.example.rest_api.adapter.out.repository.UserRepository;
import com.example.rest_api.domain.service.OAuthService;
import com.example.rest_api.domain.user.AuthenticationDTO;
import com.example.rest_api.domain.user.LoginResponseDTO;
import com.example.rest_api.domain.user.OAuthResponse;
import com.example.rest_api.domain.user.RegisterDTO;
import com.example.rest_api.domain.user.User;
import com.example.rest_api.config.security.TokenService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("auth")
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AuthenticationDTO data) {
        log.info("Authentication login user");
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        log.info("Authentication register user role " + data.role());
        if (this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPasword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPasword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @RequestMapping("/auth/oauth2")
    public class OAuth2Controller {

        private final OAuthService oAuthService;
        private final User userService;
        private final TokenService tokenService;

        public OAuth2Controller(OAuthService oAuthService, User userService, TokenService tokenService) {
            this.oAuthService = oAuthService;
            this.userService = userService;
            this.tokenService = tokenService;
        }

        @PostMapping("/google")
        public ResponseEntity<OAuthResponse> googleLogin(@RequestBody Map<String, String> body) {
            String code = body.get("code");

            // Step 1: Exchange code for access token
            var tokenResponse = oAuthService.exchangeCodeForTokens(code);
            String accessToken = tokenResponse.accessToken();

            // Step 2: Fetch user info from Google
            var userInfo = oAuthService.getUserInfo(accessToken);

            // Step 3: Check if user exists or create a new one
            User user = userService;

            // Step 4: Generate JWT for authenticated user
            String jwt = tokenService.generateToken(user);

            return ResponseEntity.ok(new OAuthResponse(jwt));
        }
    }
}

