package mg.extedim.prodforecastback.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "productions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Production {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "dossier_id", nullable = false)
    private Dossier dossier;

    @ManyToOne
    @JoinColumn(name = "etape_id", nullable = false)
    private Etape etape;

    @ManyToOne
    @JoinColumn(name = "operateur_id", nullable = false)
    private User operateur;

    private LocalDate dateSaisie;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Integer nbEnregistrements;
    private Integer dureeNette;
    private Integer dureePause;
    private String poste;
    private String typeDossier;
}

