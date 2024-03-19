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
@Table(name="catalogo_nivel_soporte")
@Data
public class NivelSoporte {
    @Id
    @Column(name = "id_nivel_soporte")
    Integer idNivel;
    @Column(name = "nivel")
    String nivel;

}
