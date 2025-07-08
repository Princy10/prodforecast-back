package mg.extedim.prodforecastback.repository;

import mg.extedim.prodforecastback.model.Etape;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EtapeRepository extends JpaRepository<Etape, UUID> {}
