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
@Table(name="catalogo_urgencias_ob")
@Data
public class UrgenciasOB {
    @Id
    @Column(name = "id_urgencia_ob")
    Integer idUrgencia;
    @Column(name = "urgencia_ob")
    String urgencia;

}
