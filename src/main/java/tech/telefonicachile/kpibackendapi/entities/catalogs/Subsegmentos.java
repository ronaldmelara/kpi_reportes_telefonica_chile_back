package tech.telefonicachile.kpibackendapi.entities.catalogs;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="catalogo_subsegmentos")
@Data
public class Subsegmentos {
    @Id
    @Column(name = "id_subsegmento")
    Integer idSubsegmento;
    @Column(name = "subsegmento")
    String subsegmento;

}
