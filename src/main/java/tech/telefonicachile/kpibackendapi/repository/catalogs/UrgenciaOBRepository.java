package tech.telefonicachile.kpibackendapi.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.entities.catalogs.UrgenciasOB;

@Repository
public interface UrgenciaOBRepository extends JpaRepository<UrgenciasOB, Long> {
    UrgenciasOB findByUrgencia(String param);
}
