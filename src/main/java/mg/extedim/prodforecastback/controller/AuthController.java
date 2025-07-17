package mg.extedim.prodforecastback.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mg.extedim.prodforecastback.dto.AuthResponse;
import mg.extedim.prodforecastback.dto.LoginRequest;
import mg.extedim.prodforecastback.dto.RegisterRequest;
import mg.extedim.prodforecastback.dto.UserInfoDto;
import mg.extedim.prodforecastback.model.User;
import mg.extedim.prodforecastback.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        AuthResponse authResponse = authService.login(request);

        Cookie cookie = new Cookie("accessToken", authResponse.getAccessToken());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);

        response.addCookie(cookie);

        return ResponseEntity.ok(
                Map.of(
                "expireAt", authResponse.getExpireAt(),
                "user", authResponse.getUser()
                )
        );
    }

    @GetMapping("/me")
    public ResponseEntity<UserInfoDto> me(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        UserInfoDto dto = new UserInfoDto(
                user.getId(),
                user.getEmail(),
                user.getNom(),
                user.getPrenom(),
                user.getRole().getNom().name(),
                user.getPhotoUrl()
        );
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("accessToken", "");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);

        response.addCookie(cookie);
        return ResponseEntity.ok(Map.of("message", "Déconnexion réussie"));
    }
}