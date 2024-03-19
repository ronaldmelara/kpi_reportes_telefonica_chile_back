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
@Table(name="catalogo_servicios_iso")
@Data
public class ServiciosISO {
    @Id
    @Column(name = "id_servicio_iso")
    Integer idServicio;
    @Column(name = "servicio_iso")
    String servicio;

}
