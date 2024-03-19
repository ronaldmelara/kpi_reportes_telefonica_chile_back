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
@Table(name="catalogo_codigo_servicios")
@Data
public class CodigoServicios {
    @Id
    @Column(name = "id_codigo_servicio")
    Integer idCodigoServicio;
    @Column(name = "codigo")
    String codigoServicio;

}
