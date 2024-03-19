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
@Table(name="catalogo_tipo_servicios")
@Data
public class TipoServicios {
    @Id
    @Column(name = "id_tipo_servicio")
    Integer idTipoServicio;
    @Column(name = "servicio")
    String servicio;

}
