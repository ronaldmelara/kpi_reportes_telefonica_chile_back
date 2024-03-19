package tech.telefonicachile.kpibackendapi.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.entities.catalogs.GrupoPropietario;

@Repository
public interface GrupoPropietarioRepository extends JpaRepository<GrupoPropietario, Long> {
    GrupoPropietario findByGrupo(String param);
}
