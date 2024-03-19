package tech.telefonicachile.kpibackendapi.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.entities.catalogs.CodigoServicios;

@Repository
public interface CodigoServiciosRepository extends JpaRepository<CodigoServicios, Long> {
    CodigoServicios findByCodigoServicio(String param);
}
