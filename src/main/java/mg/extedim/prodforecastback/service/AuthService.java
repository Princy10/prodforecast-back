package mg.extedim.prodforecastback.service;

import lombok.RequiredArgsConstructor;
import mg.extedim.prodforecastback.dto.AuthResponse;
import mg.extedim.prodforecastback.dto.LoginRequest;
import mg.extedim.prodforecastback.dto.RegisterRequest;
import mg.extedim.prodforecastback.exception.*;
import mg.extedim.prodforecastback.model.Role;
import mg.extedim.prodforecastback.model.User;
import mg.extedim.prodforecastback.repository.RoleRepository;
import mg.extedim.prodforecastback.repository.UserRepository;
import mg.extedim.prodforecastback.security.jwt.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException("Cet email est déjà utilisé.");
        }

        Role role = roleRepository.findByNom(request.getRoleName())
                .orElseThrow(() -> new RoleNotFoundException("Rôle non trouvé : " + request.getRoleName()));

        // Création et sauvegarde du nouvel utilisateur
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setRole(role);
        user.setActif(true);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);

        String token = jwtService.generateToken(Map.of("role", role.getNom().name()), user.getEmail());
        return new AuthResponse(token, "Bearer");
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Aucun utilisateur trouvé avec cet email"));

        if (!user.isActif()) {
            throw new UserNotActiveException("Compte désactivé, contactez un administrateur.");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Mot de passe incorrect");
        }

        String token = jwtService.generateToken(Map.of("role", user.getRole().getNom().name()), user.getEmail());
        return new AuthResponse(token, "Bearer");
    }
}
