package mg.extedim.prodforecastback.repository.postgres;

import mg.extedim.prodforecastback.model.postgres.Production;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductionRepository extends JpaRepository<Production, UUID> {}