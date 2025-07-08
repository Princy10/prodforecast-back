package mg.extedim.prodforecastback.repository;

import mg.extedim.prodforecastback.model.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DossierRepository extends JpaRepository<Dossier, UUID> {}
