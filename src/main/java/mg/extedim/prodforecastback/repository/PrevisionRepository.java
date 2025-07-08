package mg.extedim.prodforecastback.repository;

import mg.extedim.prodforecastback.model.Prevision;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrevisionRepository extends JpaRepository<Prevision, UUID> {}
