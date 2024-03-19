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
@Table(name="catalogo_catpresol1")
@Data
public class Catpresol1 {
    @Id
    @Column(name = "id_catpresol1")
    Integer idCatpresol1;
    @Column(name = "catpresol1")
    String catpresol1;

}
