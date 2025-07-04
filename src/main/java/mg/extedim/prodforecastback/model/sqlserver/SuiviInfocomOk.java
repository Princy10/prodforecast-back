package mg.extedim.prodforecastback.model.sqlserver;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "suivi_infocom_ok", schema = "dbo")
@Data
public class SuiviInfocomOk {
    @Id
    private Integer id;

    private String Dossier;
    private Integer DurSai;
    private LocalDateTime DateSaisie;
    private String saisie;
    private String type_dossier;
    private String poste;
    private Integer nbenr;
    private LocalTime hdeb;
    private LocalTime hfin;
    private Integer dureenette;
    private Integer dureepause;
}
