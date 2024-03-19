package tech.telefonicachile.kpibackendapi.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.entities.catalogs.Catpresol1;

@Repository
public interface Catpresol1Repository extends JpaRepository<Catpresol1, Long> {
    Catpresol1 findByCatpresol1(String param);
}
