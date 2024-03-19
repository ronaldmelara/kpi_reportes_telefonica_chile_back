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
@Table(name="catalogo_tipo_servicio_rmdy")
@Data
public class TipoServicioRemedy {
    @Id
    @Column(name = "id_tipo_servicio_rmdy")
    Integer idTipoServicio;
    @Column(name = "tipo_servicio_rmdy")
    String tipoServicio;

}
