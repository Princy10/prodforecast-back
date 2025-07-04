package mg.extedim.prodforecastback.repository.postgres;

import mg.extedim.prodforecastback.model.postgres.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {}