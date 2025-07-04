package mg.extedim.prodforecastback.model.postgres;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "previsions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prevision {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private LocalDate date;
    private String typePrevision; // JOUR, SEMAINE, MOIS
    private Integer valeurPrevue;
    private Integer valeurReelle;

    @ManyToOne
    @JoinColumn(name = "dossier_id", nullable = false)
    private Dossier dossier;

    @ManyToOne
    @JoinColumn(name = "etape_id", nullable = false)
    private Etape etape;
}
