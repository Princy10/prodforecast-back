package mg.extedim.prodforecastback.dto;

import lombok.Data;
import mg.extedim.prodforecastback.model.RoleName;

@Data
public class RegisterRequest {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private RoleName roleName;
}
