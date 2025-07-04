package mg.extedim.prodforecastback.model.postgres;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String titre;
    @Column(columnDefinition = "text")
    private String message;
    private LocalDateTime dateCreation;
    private Boolean vue;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private User utilisateur; // peut être null pour une notification globale
}

