package tech.telefonicachile.kpibackendapi.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.entities.catalogs.ClasificacionTipoServicios;

@Repository
public interface ClasificacionTipoServRepository extends JpaRepository<ClasificacionTipoServicios, Long> {
    ClasificacionTipoServicios findByClasificacion(String param);
}
