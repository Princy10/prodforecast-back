package mg.extedim.prodforecastback.repository.postgres;

import mg.extedim.prodforecastback.model.postgres.Etape;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EtapeRepository extends JpaRepository<Etape, UUID> {}
