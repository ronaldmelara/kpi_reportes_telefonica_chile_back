package tech.telefonicachile.kpibackendapi.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.entities.catalogs.TipoServicios;

@Repository
public interface TipoServiciosRepository extends JpaRepository<TipoServicios, Long> {
    TipoServicios findByServicio(String param);
}
