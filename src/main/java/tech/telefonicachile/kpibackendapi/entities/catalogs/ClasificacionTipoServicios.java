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
@Table(name="catalogo_clasificacion_tipos_servicio")
@Data
public class ClasificacionTipoServicios {
    @Id
    @Column(name = "id_clasf_tipo_servicio")
    Integer idClasificacion;
    @Column(name = "clasificacion_tipo_servicio")
    String clasificacion;

}
