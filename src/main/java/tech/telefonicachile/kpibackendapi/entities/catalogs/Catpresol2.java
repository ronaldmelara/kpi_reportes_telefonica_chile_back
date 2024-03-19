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
@Table(name="catalogo_catpresol2")
@Data
public class Catpresol2 {
    @Id
    @Column(name = "id_catpresol2")
    Integer idCatpresol2;
    @Column(name = "catpresol2")
    String catpresol2;

}
