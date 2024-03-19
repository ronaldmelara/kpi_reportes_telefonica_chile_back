package tech.telefonicachile.kpibackendapi.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.entities.catalogs.Segmentos;

@Repository
public interface SegmentosRepository extends JpaRepository<Segmentos, Long> {
    Segmentos findBySegmento(String param);
}
