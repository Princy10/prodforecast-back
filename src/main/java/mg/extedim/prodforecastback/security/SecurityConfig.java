package mg.extedim.prodforecastback.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Désactive CSRF (ok en REST, à réactiver si site web classique !)
                .csrf(csrf -> csrf.disable())

                // (Optionnel mais recommandé) : active CORS si tu as un front sur un autre port/domaine
                .cors(cors -> {})

                .authorizeHttpRequests(auth -> auth
                        // Autorise docs publiques (swagger, si tu ajoutes openapi plus tard)
                        .requestMatchers(
                                "/api/auth/register",
                                "/api/auth/login",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()
                        // Protéger tout le reste
                        .anyRequest().authenticated()
                );

        // (Optionnel, pour permettre le H2-console si tu fais des tests locaux)
        // http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        return http.build();
    }
}
