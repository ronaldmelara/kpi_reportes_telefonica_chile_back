package tech.telefonicachile.kpibackendapi.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.entities.catalogs.TipoServicioRemedy;

@Repository
public interface TipoServicioRemedyRepository extends JpaRepository<TipoServicioRemedy, Long> {
    TipoServicioRemedy findByTipoServicio(String param);
}
