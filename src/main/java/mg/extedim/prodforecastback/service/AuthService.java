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
        // Vérifie si l'email existe déjà
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException("Cet email est déjà utilisé.");
        }

        // Vérifie que le rôle demandé existe
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

        return new AuthResponse(null, "Utilisateur créé avec succès");
    }

    public AuthResponse login(LoginRequest request) {
        // Recherche l'utilisateur par email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Aucun utilisateur trouvé avec cet email"));

        if (!user.isActif()) {
            throw new UserNotActiveException("Compte désactivé, contactez un administrateur.");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Mot de passe incorrect");
        }

        return new AuthResponse(null, "Authentification réussie");
    }
}
