package tech.telefonicachile.kpibackendapi.repository.catalogs;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.entities.catalogs.TipoIncidencias;

@Repository
public interface TipoIncidenciaRepository extends JpaRepository<TipoIncidencias, Long> {

    TipoIncidencias findByTipo(String param);

}
