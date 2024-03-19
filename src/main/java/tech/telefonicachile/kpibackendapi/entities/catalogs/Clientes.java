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
@Table(name="catalogo_clientes")
@Data
public class Clientes {
    @Id
    @Column(name = "id_cliente")
    Integer idCliente;
    @Column(name = "cliente")
    String cliente;

}
