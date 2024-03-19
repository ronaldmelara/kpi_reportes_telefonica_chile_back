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
@Table(name="catalogo_grupo_asignado")
@Data
public class GrupoAsignado {
    @Id
    @Column(name = "id_grupo_asignacion")
    Integer idGrupo;
    @Column(name = "grupo_asignacion")
    String grupo;

}
