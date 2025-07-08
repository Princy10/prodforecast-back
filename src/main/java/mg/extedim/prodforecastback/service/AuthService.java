package mg.extedim.prodforecastback.service;

import lombok.RequiredArgsConstructor;
import mg.extedim.prodforecastback.dto.AuthResponse;
import mg.extedim.prodforecastback.dto.LoginRequest;
import mg.extedim.prodforecastback.dto.RegisterRequest;
import mg.extedim.prodforecastback.model.Role;
import mg.extedim.prodforecastback.model.User;
import mg.extedim.prodforecastback.repository.RoleRepository;
import mg.extedim.prodforecastback.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }

        Role role = roleRepository.findByNom(request.getRoleName())
                .orElseThrow(() -> new RuntimeException("Role non trouvé"));

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setRole(role);
        user.setActif(true);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);

        return new AuthResponse(null, "User created");
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        if (!user.isActif()) {
            throw new RuntimeException("Compte désactivé");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        return new AuthResponse(null, "Login OK");
    }
}
