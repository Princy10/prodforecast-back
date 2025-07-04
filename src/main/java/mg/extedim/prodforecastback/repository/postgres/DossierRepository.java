package mg.extedim.prodforecastback.repository.postgres;

import mg.extedim.prodforecastback.model.postgres.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DossierRepository extends JpaRepository<Dossier, UUID> {}
