package tech.telefonicachile.kpibackendapi.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.entities.catalogs.Prioridad;

@Repository
public interface PrioridadRepository extends JpaRepository<Prioridad, Long> {
    Prioridad findByPrioridad(String param);
}
