package mg.extedim.prodforecastback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserInfoDto {
    private UUID id;
    private String email;
    private String nom;
    private String prenom;
    private String role;
    private String photoUrl;
}
