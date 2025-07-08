package mg.extedim.prodforecastback.repository;

import mg.extedim.prodforecastback.model.Role;
import mg.extedim.prodforecastback.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByNom(RoleName nom);
}
