package tech.telefonicachile.kpibackendapi.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.entities.catalogs.GrupoAsignado;

@Repository
public interface GrupoAsignadoRepository extends JpaRepository<GrupoAsignado, Long> {
    GrupoAsignado findByGrupo(String param);
}
