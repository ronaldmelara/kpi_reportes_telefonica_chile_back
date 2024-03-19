package tech.telefonicachile.kpibackendapi.repository.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.telefonicachile.kpibackendapi.entities.catalogs.Subsegmentos;

@Repository
public interface SubsegmentosRepository extends JpaRepository<Subsegmentos, Long> {
    Subsegmentos findBySubsegmento(String param);
}
