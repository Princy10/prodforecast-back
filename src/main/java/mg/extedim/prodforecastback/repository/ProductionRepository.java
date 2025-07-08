package mg.extedim.prodforecastback.repository;

import mg.extedim.prodforecastback.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductionRepository extends JpaRepository<Production, UUID> {}