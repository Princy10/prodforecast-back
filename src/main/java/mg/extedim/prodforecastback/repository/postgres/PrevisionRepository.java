package mg.extedim.prodforecastback.repository.postgres;

import mg.extedim.prodforecastback.model.postgres.Prevision;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrevisionRepository extends JpaRepository<Prevision, UUID> {}
