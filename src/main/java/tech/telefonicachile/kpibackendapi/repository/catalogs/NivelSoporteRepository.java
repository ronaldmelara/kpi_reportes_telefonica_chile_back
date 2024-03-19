package tech.telefonicachile.kpibackendapi.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.entities.catalogs.NivelSoporte;

@Repository
public interface NivelSoporteRepository extends JpaRepository<NivelSoporte, Long> {
    NivelSoporte findByNivel(String param);
}
