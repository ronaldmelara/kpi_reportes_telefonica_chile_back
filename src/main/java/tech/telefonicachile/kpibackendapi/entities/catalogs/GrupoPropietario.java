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
@Table(name="catalogo_grupo_propietario")
@Data
public class GrupoPropietario {
    @Id
    @Column(name = "id_grupo_propietario")
    Integer idGrupo;
    @Column(name = "grupo_propietario")
    String grupo;

}
