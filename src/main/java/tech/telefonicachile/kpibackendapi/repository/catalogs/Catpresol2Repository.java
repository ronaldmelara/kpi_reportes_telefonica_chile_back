package tech.telefonicachile.kpibackendapi.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.entities.catalogs.Catpresol2;

@Repository
public interface Catpresol2Repository extends JpaRepository<Catpresol2, Long> {
    Catpresol2 findByCatpresol2(String param);
}
