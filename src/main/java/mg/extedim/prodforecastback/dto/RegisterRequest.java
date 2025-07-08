package mg.extedim.prodforecastback.dto;

import lombok.Data;
import mg.extedim.prodforecastback.model.RoleName;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
public class RegisterRequest {
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;
    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;
    @Email(message = "Email non valide")
    @NotBlank(message = "Email obligatoire")
    private String email;
    @NotBlank(message = "Mot de passe obligatoire")
    private String password;
    private RoleName roleName;
}
