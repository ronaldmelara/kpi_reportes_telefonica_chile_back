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
@Table(name="catalogo_tipo_incidencia")
@Data
public class TipoIncidencias {
    @Id
    @Column(name = "id_tipo_incidencia")
    Integer idTipoIncidencia;
    @Column(name = "tipo")
    String tipo;

}
