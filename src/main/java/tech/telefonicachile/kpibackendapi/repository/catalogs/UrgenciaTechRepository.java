package tech.telefonicachile.kpibackendapi.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.entities.catalogs.UrgenciasTech;

@Repository
public interface UrgenciaTechRepository extends JpaRepository<UrgenciasTech, Long> {
    UrgenciasTech findByUrgencia(String param);
}
