package tech.telefonicachile.kpibackendapi.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.entities.catalogs.ServiciosISO;

@Repository
public interface ServiciosISORepository extends JpaRepository<ServiciosISO, Long> {
    ServiciosISO findByServicio(String param);
}
